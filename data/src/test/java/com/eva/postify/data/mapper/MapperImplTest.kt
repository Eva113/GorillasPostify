package com.eva.postify.data.mapper

import com.eva.postify.TestData
import junit.framework.Assert.assertEquals
import org.junit.Test

class MapperImplTest {
    private val mapper = MapperImpl()

    @Test
    fun `test post model mapping`() {
        assertEquals(TestData.listOfPosts, mapper.mapPosts(TestData.listOfPostModels))
    }

    @Test
    fun `test details model mapping`() {
        assertEquals(TestData.postDetails, mapper.mapDetails(TestData.postDetailsModel))
    }

}