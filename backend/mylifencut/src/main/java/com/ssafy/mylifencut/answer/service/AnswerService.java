package com.ssafy.mylifencut.answer.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ssafy.mylifencut.answer.domain.Answer;
import com.ssafy.mylifencut.answer.domain.State;
import com.ssafy.mylifencut.answer.dto.GalleryResponse;
import com.ssafy.mylifencut.answer.dto.MusicResponse;
import com.ssafy.mylifencut.answer.exception.GalleryNotFoundException;
import com.ssafy.mylifencut.answer.repository.AnswerRepository;
import com.ssafy.mylifencut.like.domain.IsLike;
import com.ssafy.mylifencut.like.repository.LikeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerService {
	private final AnswerRepository answerRepository;
	private final LikeRepository likeRepository;

	public List<GalleryResponse> getGalleryList(Integer userId) {
		List<GalleryResponse> galleryResponses = answerRepository.findAllByState(State.OPEN).stream()
			.map(GalleryResponse::of)
			.collect(Collectors.toList());

		for (GalleryResponse galleryResponse : galleryResponses) {
			Optional<IsLike> result = likeRepository.findByUserIdAndAnswerId(userId,
				galleryResponse.getAnswerId());
			if (result.isPresent()) {
				galleryResponse.setIsMine();
			}
		}

		return galleryResponses;
	}

	public GalleryResponse getGalleryOne(int userId, int answerId) {
		Answer answer = answerRepository.findById(answerId)
			.orElseThrow(GalleryNotFoundException::new);
		GalleryResponse galleryResponse = GalleryResponse.of(answer);
		if (likeRepository.findByUserIdAndAnswerId(userId, answerId).isPresent()) {
			galleryResponse.setIsMine();
		}
		return galleryResponse;
	}

	public List<MusicResponse> searchMusic(String requestUri) throws Exception {
		List<MusicResponse> musicResponseList = new ArrayList<>();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.valueOf("text/plain;charset=utf-8"));
		headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)" +
			" AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		ResponseEntity<String> result = restTemplate.exchange(requestUri, HttpMethod.GET,
			new HttpEntity<String>(headers), String.class);

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
		documentBuilderFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		final InputStream stream = new ByteArrayInputStream(
			Objects.requireNonNull(result.getBody()).getBytes(StandardCharsets.UTF_8));
		Document doc = documentBuilder.parse(stream);
		doc.getDocumentElement().normalize();
		NodeList itemList = doc.getElementsByTagName("item");
		for (int i = 0; i < itemList.getLength(); i++) {
			Node node = itemList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element)node;
				musicResponseList.add(MusicResponse.of(getTagValue("title", element), getTagValue("image", element),
					getTagValue("name", element)));
			}
		}

		return musicResponseList;
	}

	private String getTagValue(String tag, Element element) {
		NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = nodeList.item(0);
		if (node == null)
			return null;
		return node.getNodeValue();
	}
}
