package com.zecdev.starwarspeople

import android.support.test.runner.AndroidJUnit4
import com.zecdev.starwarspeople.model.HomeWorld
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeWorldTest {
    @Test
    fun unarchive() {
        val dataReceived = "{\n" +
                "    \"name\": \"Tatooine\", \n" +
                "    \"rotation_period\": \"23\", \n" +
                "    \"orbital_period\": \"304\", \n" +
                "    \"diameter\": \"10465\", \n" +
                "    \"climate\": \"arid\", \n" +
                "    \"gravity\": \"1 standard\", \n" +
                "    \"terrain\": \"desert\", \n" +
                "    \"surface_water\": \"1\", \n" +
                "    \"population\": \"200000\", \n" +
                "    \"residents\": [\n" +
                "        \"https://swapi.co/api/people/1/\", \n" +
                "        \"https://swapi.co/api/people/2/\", \n" +
                "        \"https://swapi.co/api/people/4/\", \n" +
                "        \"https://swapi.co/api/people/6/\", \n" +
                "        \"https://swapi.co/api/people/7/\", \n" +
                "        \"https://swapi.co/api/people/8/\", \n" +
                "        \"https://swapi.co/api/people/9/\", \n" +
                "        \"https://swapi.co/api/people/11/\", \n" +
                "        \"https://swapi.co/api/people/43/\", \n" +
                "        \"https://swapi.co/api/people/62/\"\n" +
                "    ], \n" +
                "    \"films\": [\n" +
                "        \"https://swapi.co/api/films/5/\", \n" +
                "        \"https://swapi.co/api/films/4/\", \n" +
                "        \"https://swapi.co/api/films/6/\", \n" +
                "        \"https://swapi.co/api/films/3/\", \n" +
                "        \"https://swapi.co/api/films/1/\"\n" +
                "    ], \n" +
                "    \"created\": \"2014-12-09T13:50:49.641000Z\", \n" +
                "    \"edited\": \"2014-12-21T20:48:04.175778Z\", \n" +
                "    \"url\": \"https://swapi.co/api/planets/1/\"\n" +
                "}"

        val result : HomeWorld = HomeWorld.unarchive(dataReceived)!!
        Assert.assertNotNull(result)

        Assert.assertEquals(result!!.id, 1)
        Assert.assertEquals(result.name, "Tatooine")
    }
}
