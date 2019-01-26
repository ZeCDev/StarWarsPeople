package com.zecdev.starwarspeople

import android.support.test.runner.AndroidJUnit4
import com.zecdev.starwarspeople.model.Specie
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SpecieTest {
    @Test
    fun unarchive() {
        val dataReceived = "{\n" +
                "    \"count\": 37, \n" +
                "    \"next\": \"https://swapi.co/api/species/?page=2\", \n" +
                "    \"previous\": null, \n" +
                "    \"results\": [\n" +
                "        {\n" +
                "            \"name\": \"Hutt\", \n" +
                "            \"classification\": \"gastropod\", \n" +
                "            \"designation\": \"sentient\", \n" +
                "            \"average_height\": \"300\", \n" +
                "            \"skin_colors\": \"green, brown, tan\", \n" +
                "            \"hair_colors\": \"n/a\", \n" +
                "            \"eye_colors\": \"yellow, red\", \n" +
                "            \"average_lifespan\": \"1000\", \n" +
                "            \"homeworld\": \"https://swapi.co/api/planets/24/\", \n" +
                "            \"language\": \"Huttese\", \n" +
                "            \"people\": [\n" +
                "                \"https://swapi.co/api/people/16/\"\n" +
                "            ], \n" +
                "            \"films\": [\n" +
                "                \"https://swapi.co/api/films/3/\", \n" +
                "                \"https://swapi.co/api/films/1/\"\n" +
                "            ], \n" +
                "            \"created\": \"2014-12-10T17:12:50.410000Z\", \n" +
                "            \"edited\": \"2014-12-20T21:36:42.146000Z\", \n" +
                "            \"url\": \"https://swapi.co/api/species/5/\"\n" +
                "        }, \n" +
                "    ]\n" +
                "}"

        val result : HashMap<Int, Specie> = Specie.unarchive(dataReceived)
        Assert.assertEquals(result.size, 1);
        var specie = result.get(5);
        Assert.assertNotNull(specie)

        Assert.assertEquals(specie!!.designation, "sentient")
        Assert.assertEquals(specie.id, 5)
        Assert.assertEquals(specie.name, "Hutt")
    }
}
