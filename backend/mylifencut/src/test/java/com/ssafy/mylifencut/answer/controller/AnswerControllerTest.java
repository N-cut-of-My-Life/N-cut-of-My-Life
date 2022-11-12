package com.ssafy.mylifencut.answer.controller;

import com.google.gson.Gson;
import com.ssafy.mylifencut.answer.AnswerConstant;
import com.ssafy.mylifencut.answer.dto.GalleryResponse;
import com.ssafy.mylifencut.answer.service.AnswerService;
import com.ssafy.mylifencut.common.aop.ExceptionAdvice;
import com.ssafy.mylifencut.common.dto.BaseResponse;
import com.ssafy.mylifencut.like.LikeConstant;
import com.ssafy.mylifencut.like.dto.IsLikeResponse;
import com.ssafy.mylifencut.like.exception.AlreadyLikeException;
import com.ssafy.mylifencut.like.exception.NotExistLikeException;
import com.ssafy.mylifencut.like.service.LikeService;
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

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
	class LikeRegisterTest{
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
	class DeleteLikeTest{
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
	class ReadGalleryTest{
		@Test
		@DisplayName("[성공] - 갤러리 조회")
		void readGallery() throws Exception {
			//given
			final String url = "/answer";
			doReturn(Arrays.asList(
					GalleryResponse.builder().id(1).userId(3).answerId(3).contents("답변내용").like(10).build(),
					GalleryResponse.builder().id(2).userId(4).answerId(4).contents("답변내용이지롱").like(11).build(),
					GalleryResponse.builder().id(3).userId(5).answerId(5).contents("답변내용입니당").like(12).build()
			)).when(answerService).getGalleryList();
			//when
			final ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.get(url)
			);
			final BaseResponse response =  gson.fromJson(resultActions.andReturn()
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
				assertEquals((double) (i + 1), galleryResponse.get("id"));
				assertEquals((double) (i + 3), galleryResponse.get("userId"));
				assertEquals((double) (i + 10), galleryResponse.get("like"));
				assertEquals((double) (i + 3), galleryResponse.get("answerId"));
				assertEquals(contentsList[i], galleryResponse.get("contents"));
			}
		}
	}

}