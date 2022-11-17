package com.ssafy.mylifencut.answer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.ssafy.mylifencut.answer.AnswerConstant;
import com.ssafy.mylifencut.answer.dto.GalleryResponse;
import com.ssafy.mylifencut.answer.dto.MusicResponse;
import com.ssafy.mylifencut.answer.exception.GalleryNotFoundException;
import com.ssafy.mylifencut.answer.service.AnswerService;
import com.ssafy.mylifencut.answer.util.KeyWordConverterToURI;
import com.ssafy.mylifencut.common.aop.ExceptionAdvice;
import com.ssafy.mylifencut.common.dto.BaseResponse;
import com.ssafy.mylifencut.like.LikeConstant;
import com.ssafy.mylifencut.like.dto.IsLikeResponse;
import com.ssafy.mylifencut.like.exception.AlreadyLikeException;
import com.ssafy.mylifencut.like.exception.NotExistLikeException;
import com.ssafy.mylifencut.like.service.LikeService;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
@DisplayName("[답변 컨트롤러]")
class AnswerControllerTest {

	@InjectMocks
	private AnswerController answerController;

	@Mock
	private AnswerService answerService;

	@Mock
	private LikeService likeService;

	private MockMvc mockMvc;
	private Gson gson;

	@BeforeEach
	public void init() {
		gson = new Gson();
		mockMvc = MockMvcBuilders.standaloneSetup(answerController)
			.setControllerAdvice(new ExceptionAdvice())
			.build();
	}

	@Nested
	@DisplayName("[좋아요 추가]")
	class LikeRegisterTest {
		@Test
		@DisplayName("[실패] - 이미 좋아요가 추가된 답변에 좋아요 등록")
		void alreadyLike() throws Exception {
			//given
			final String url = "/answer/3/1";
			final Integer answerId = 3;
			final Integer userId = 1;

			doThrow(new AlreadyLikeException())
				.when(likeService)
				.createLike(userId, answerId);
			//when
			final ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.post(url)
					.contentType(MediaType.APPLICATION_JSON)
			);
			//then
			resultActions.andExpect(status().isBadRequest());

			final BaseResponse response = gson.fromJson(resultActions.andReturn()
				.getResponse()
				.getContentAsString(StandardCharsets.UTF_8), BaseResponse.class);
			Map map = (Map)response.getData();
			assertNull(response.getData());
			assertFalse(response.isSuccess());
			assertEquals(LikeConstant.ALREADY_LIKE_EXIST_ERROR_MESSAGE, response.getMessage());

		}

		@Test
		@DisplayName("[성공] - 좋아요 추가")
		void createLike() throws Exception {
			//given
			final String url = "/answer/3/1";
			final Integer answerId = 3;
			final Integer userId = 1;
			final IsLikeResponse isLikeResponse = IsLikeResponse.builder()
				.id(1)
				.answerId(answerId)
				.userId(userId)
				.build();
			doReturn(isLikeResponse).when(likeService).createLike(userId, answerId);
			//when
			final ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.post(url)
					.contentType(MediaType.APPLICATION_JSON)
			);
			//then
			resultActions.andExpect(status().isOk());
			final BaseResponse response = gson.fromJson(resultActions.andReturn()
				.getResponse()
				.getContentAsString(StandardCharsets.UTF_8), BaseResponse.class);
			Map map = (Map)response.getData();
			assertEquals((double)isLikeResponse.getId(), map.get("id"));
			assertEquals((double)isLikeResponse.getAnswerId(), map.get("answerId"));
			assertEquals((double)isLikeResponse.getUserId(), map.get("userId"));
			assertTrue(response.isSuccess());
			assertEquals(LikeConstant.CREATE_LIKE_SUCCESS_MESSAGE, response.getMessage());

		}
	}

	@Nested
	@DisplayName("[좋아요 삭제]")
	class DeleteLikeTest {
		@Test
		@DisplayName("[실패] - 좋아요가 눌리지 않은 답변에 좋아요 삭제 시도")
		void notExistLike() throws Exception {
			//given
			final String url = "/answer/3/1";
			final Integer answerId = 3;
			final Integer userId = 1;
			doThrow(new NotExistLikeException())
				.when(likeService)
				.deleteLike(userId, answerId);
			//when
			final ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.delete(url)
					.contentType(MediaType.APPLICATION_JSON)
			);
			//then
			resultActions.andExpect(status().isBadRequest());
			final BaseResponse response = gson.fromJson(resultActions.andReturn()
				.getResponse()
				.getContentAsString(StandardCharsets.UTF_8), BaseResponse.class);
			Map map = (Map)response.getData();
			assertNull(response.getData());
			assertFalse(response.isSuccess());
			assertEquals(LikeConstant.NOT_EXIST_LIKE_ERROR_MESSAGE, response.getMessage());

		}

		@Test
		@DisplayName("[성공] - 좋아요 삭제")
		void deleteLike() throws Exception {
			//given
			final String url = "/answer/3/1";
			//when
			final ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.delete(url)
			);
			//then
			resultActions.andExpect(status().isNoContent());
			final BaseResponse response = gson.fromJson(resultActions.andReturn()
				.getResponse()
				.getContentAsString(StandardCharsets.UTF_8), BaseResponse.class);
			Map map = (Map)response.getData();
			assertNull(response.getData());
			assertTrue(response.isSuccess());
			assertEquals(LikeConstant.DELETE_LIKE_SUCCESS_MESSAGE, response.getMessage());

		}
	}

	@Nested
	@DisplayName("[갤러리 조회]")
	class ReadGalleryTest {
		@Test
		@DisplayName("[성공] - 갤러리 조회")
		void readGallery() throws Exception {
			//given
			final String url = "/answer/3";
			final Integer userId = 3;
			doReturn(Arrays.asList(
				GalleryResponse.builder()
					.id(1)
					.userId(3)
					.answerId(3)
					.contents("답변내용")
					.imgUrl("src/image")
					.like(10)
					.build(),
				GalleryResponse.builder()
					.id(2)
					.userId(4)
					.answerId(4)
					.contents("답변내용이지롱")
					.imgUrl("src/image")
					.like(11)
					.build(),
				GalleryResponse.builder()
					.id(3)
					.userId(5)
					.answerId(5)
					.contents("답변내용입니당")
					.imgUrl("src/image")
					.like(12)
					.build()
			)).when(answerService).getGalleryList(userId);
			//when
			final ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.get(url)
			);
			final BaseResponse response = gson.fromJson(resultActions.andReturn()
				.getResponse()
				.getContentAsString(StandardCharsets.UTF_8), BaseResponse.class);

			//then
			resultActions.andExpect(status().isOk());
			assertNotNull(response);
			assertTrue(response.isSuccess());
			assertEquals(AnswerConstant.READ_GALLERY_SUCCESS_MESSAGE, response.getMessage());
			List<GalleryResponse> list = (List<GalleryResponse>)response.getData();
			String[] contentsList = {"답변내용", "답변내용이지롱", "답변내용입니당"};
			for (int i = 0; i < list.size(); i++) {
				Map galleryResponse = (Map) list.get(i);
				assertEquals((double)(i + 1), galleryResponse.get("id"));
				assertEquals((double)(i + 3), galleryResponse.get("userId"));
				assertEquals((double)(i + 10), galleryResponse.get("like"));
				assertEquals("src/image", galleryResponse.get("imgUrl"));
				assertEquals((double)(i + 3), galleryResponse.get("answerId"));
				assertEquals(contentsList[i], galleryResponse.get("contents"));
			}
		}
	}

	@Nested
	@DisplayName("[답변 단건 조회]")
	class GalleryReadOneTest {
		@Test
		@DisplayName("[실패] - 없는 갤러리 번호")
		void notValidGallery() throws Exception {
			// given
			final String url = "/answer/1/1";
			final int userId = 1;
			final int answerId = 1;
			doThrow(new GalleryNotFoundException())
				.when(answerService)
				.getGalleryOne(1, 1);

			// when
			final ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.get(url)
			);

			// then
			final BaseResponse response = gson.fromJson(resultActions.andReturn()
				.getResponse()
				.getContentAsString(StandardCharsets.UTF_8), BaseResponse.class);
			resultActions.andExpect(status().isBadRequest());
			assertNotNull(response);
			assertFalse(response.isSuccess());
			assertEquals(AnswerConstant.GALLERY_NOT_FOUND_ERROR_MESSAGE, response.getMessage());
		}

		@Test
		@DisplayName("[성공]")
		void validGallery() throws Exception {
			// given
			final String url = "/answer/1/1";
			final int userId = 1;
			final int answerId = 1;
			final GalleryResponse galleryResponse = GalleryResponse.builder()
				.id(1)
				.userId(1)
				.answerId(1)
				.contents("답변내용이지롱")
				.imgUrl("src/image")
				.like(11)
				.isMine(IsMine.FALSE)
				.build();
			doReturn(galleryResponse)
				.when(answerService)
				.getGalleryOne(userId, answerId);

			// when
			final ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.get(url)
			);

			// then
			final BaseResponse response = gson.fromJson(resultActions.andReturn()
				.getResponse()
				.getContentAsString(StandardCharsets.UTF_8), BaseResponse.class);
			resultActions.andExpect(status().isOk());
			assertNotNull(response);
			assertTrue(response.isSuccess());
			assertEquals(AnswerConstant.READ_GALLERY_SUCCESS_MESSAGE, response.getMessage());

			Map map = (Map)response.getData();
			assertEquals((double)galleryResponse.getId(), map.get("id"));
			assertEquals((double)galleryResponse.getUserId(), map.get("userId"));
			assertEquals((double)galleryResponse.getAnswerId(), map.get("answerId"));
			assertEquals(galleryResponse.getContents(), map.get("contents"));
			assertEquals(galleryResponse.getImgUrl(), map.get("imgUrl"));
			assertEquals((double)galleryResponse.getLike(), map.get("like"));
			assertEquals(galleryResponse.getIsMine().toString(), map.get("isMine"));
		}
	}

	@Nested
	@DisplayName("[음악 검색]")
	class SearchMusicTest {
		@Test
		@DisplayName("[성공] - 음악 검색")
		void searchMusic() throws Exception {
			//given
			final String keyword = "sky";
			final String url = "/answer/music/" + keyword;
			List<MusicResponse> musicResponseList = new ArrayList<>();
			for (int i = 0; i < 10; i++)
				musicResponseList.add(MusicResponse.builder().build());
			doReturn(musicResponseList).when(answerService).searchMusic(KeyWordConverterToURI.converter(keyword));
			//when
			final ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.get(url)
			);
			final BaseResponse response = gson.fromJson(resultActions.andReturn()
				.getResponse()
				.getContentAsString(StandardCharsets.UTF_8), BaseResponse.class);
			//then
			resultActions.andExpect(status().isOk());
			assertNotNull(response);
			assertTrue(response.isSuccess());
			assertEquals(AnswerConstant.SEARCH_MUSIC_SUCCESS_MESSAGE, response.getMessage());
		}
	}
}