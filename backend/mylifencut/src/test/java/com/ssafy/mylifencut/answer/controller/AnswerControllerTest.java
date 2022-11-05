package com.ssafy.mylifencut.answer.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

import com.ssafy.mylifencut.like.LikeConstant;
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

import com.google.gson.Gson;
import com.ssafy.mylifencut.answer.domain.State;
import com.ssafy.mylifencut.answer.dto.AnswerRegisterRequest;
import com.ssafy.mylifencut.answer.dto.AnswerResponse;
import com.ssafy.mylifencut.answer.dto.GalleryResponse;
import com.ssafy.mylifencut.answer.exception.InvalidStateException;
import com.ssafy.mylifencut.answer.service.AnswerService;
import com.ssafy.mylifencut.common.aop.ExceptionAdvice;
import com.ssafy.mylifencut.common.dto.BaseResponse;
import com.ssafy.mylifencut.like.dto.IsLikeResponse;
import com.ssafy.mylifencut.like.exception.AlreadyLikeException;
import com.ssafy.mylifencut.like.exception.NotExistLikeException;
import com.ssafy.mylifencut.like.service.LikeService;

@ExtendWith(MockitoExtension.class)
@DisplayName("답변 컨트롤러 테스트")
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

	@Test
	@DisplayName("답변 등록 실패 - 잘못된 행성에 공개여부 선택 된 경우")
	public void invalidState() throws Exception {
		// given
		final String url = "/answer";
		final AnswerRegisterRequest answerRegisterRequest =
			new AnswerRegisterRequest(1, 1, "답변 등록", State.OPEN);
		doThrow(new InvalidStateException())
			.when(answerService)
			.createAnswer(answerRegisterRequest);
		// when
		final ResultActions resultActions = mockMvc.perform(
			MockMvcRequestBuilders.post(url)
				.content(gson.toJson(answerRegisterRequest))
				.contentType(MediaType.APPLICATION_JSON)
		);
		// then
		resultActions.andExpect(status().isBadRequest());
	}
	@Test
	@DisplayName("답변 등록 성공")
	public void createAnswer() throws Exception {
		// given
		final String url = "/answer";
		final AnswerRegisterRequest answerRegisterRequest =
			new AnswerRegisterRequest(1, 1, "답변 등록", State.CLOSE);
		final AnswerResponse answerResponse = AnswerResponse.builder()
			.id(1)
			.articleId(1)
			.questionId(answerRegisterRequest.getQuestionId())
			.contents(answerRegisterRequest.getContents())
			.state(answerRegisterRequest.getState())
			.build();
		doReturn(answerResponse)
			.when(answerService)
			.createAnswer(answerRegisterRequest);

		// when
		final ResultActions resultActions = mockMvc.perform(
			MockMvcRequestBuilders.post(url)
				.content(gson.toJson(answerRegisterRequest))
				.contentType(MediaType.APPLICATION_JSON)
		);

		// then
		resultActions.andExpect(status().isOk());

		final BaseResponse response = gson.fromJson(resultActions.andReturn()
			.getResponse()
			.getContentAsString(StandardCharsets.UTF_8), BaseResponse.class);

		Map map = (Map)response.getData();
		assertEquals((double)answerResponse.getId(), map.get("id"));
		assertEquals((double)answerResponse.getArticleId(), map.get("articleId"));
		assertEquals((double)answerResponse.getQuestionId(), map.get("questionId"));
		assertEquals(answerResponse.getContents(), map.get("contents"));
		assertEquals(answerResponse.getState().toString(), map.get("state"));
	}

	@Nested
	@DisplayName("좋아요 추가 테스트")
	class LikeRegisterTest{
		@Test
		@DisplayName("좋아요 추가 실패 - 이미 좋아요가 추가된 답변에 좋아요 등록")
		public void alreadyLike() throws Exception {
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
		@DisplayName("좋아요 추가 성공")
		public void createLike() throws Exception {
			//given
			final String url = "/answer/3/1";
			final Integer answerId = 3;
			final Integer userId = 1;
			final IsLikeResponse isLikeResponse = IsLikeResponse.builder()
					.id(1)
					.answer_id(answerId)
					.user_id(userId)
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
			assertEquals((double)isLikeResponse.getAnswer_id(), map.get("answer_id"));
			assertEquals((double)isLikeResponse.getUser_id(), map.get("user_id"));
			assertTrue(response.isSuccess());
			assertEquals(LikeConstant.CREATE_LIKE_SUCCESS_MESSAGE, response.getMessage());

		}
	}

	@Nested
	@DisplayName("좋아요 삭제 테스트")
	class DeleteLikeTest{
		@Test
		@DisplayName("좋아요 삭제 실패 - 좋아요가 눌리지 않은 답변에 좋아요 삭제 시도")
		public void notExistLike() throws Exception {
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



		}
		@Test
		@DisplayName("좋아요 삭제 성공")
		public void deleteLike() throws Exception {
			//given
			final String url = "/answer/3/1";
			//when
			final ResultActions resultActions = mockMvc.perform(
					MockMvcRequestBuilders.delete(url)
			);
			//then
			resultActions.andExpect(status().isNoContent());


		}
	}

	@Nested
	@DisplayName("갤러리 조회 테스트")
	class ReadGalleryTest{
		@Test
		@DisplayName("갤러리 조회 성공")
		public void readGallery() throws Exception {
			//given
			final String url = "/answer";
			doReturn(Arrays.asList(
					GalleryResponse.builder().id(1).userId(3).contents("답변내용").like(3).build(),
					GalleryResponse.builder().id(2).userId(5).contents("답변내용이지롱").like(12).build(),
					GalleryResponse.builder().id(3).userId(6).contents("답변내용입니당").like(13).build()
			)).when(answerService).getGalleryList();
			//when
			final ResultActions resultActions = mockMvc.perform(
					MockMvcRequestBuilders.get(url)
			);
			//then
			resultActions.andExpect(status().isOk());
		}
	}

}