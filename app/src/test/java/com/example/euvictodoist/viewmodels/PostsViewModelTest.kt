package com.example.euvictodoist.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.euvictodoist.TestCoroutineRule
import com.example.euvictodoist.models.PostResponse
import com.example.euvictodoist.network.repository.PostRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class PostsViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var postsRepositoryMock: PostRepository

    private lateinit var postsViewModel: PostsViewModel

    @Before
    fun setUp() {
        postsViewModel = PostsViewModel(postsRepositoryMock)
    }

    @Test
    fun testPostsListWithData() = testCoroutineRule.runBlockingTest {
        // EXPECT
        val potsExpected = mockPostsLists()

        whenever(postsRepositoryMock.getPostsList()).thenReturn(mockPostsLists())

        // ACT
        postsViewModel.requestPostsList()

        // ASSERT
        verify(postsRepositoryMock, times(1)).getPostsList()
        Assert.assertEquals(potsExpected.first().id, postsViewModel.postList.value?.first()?.id)
    }

    @Test
    fun testPostsListNoData() = testCoroutineRule.runBlockingTest {
        whenever(postsRepositoryMock.getPostsList()).thenReturn(arrayListOf())

        // ACT
        postsViewModel.requestPostsList()

        // ASSERT
        verify(postsRepositoryMock, times(1)).getPostsList()
        Assert.assertEquals(0, postsViewModel.postList.value?.size)
    }

    private fun mockPostsLists() = arrayListOf(PostResponse(1, 1, "Test", "body"))
}