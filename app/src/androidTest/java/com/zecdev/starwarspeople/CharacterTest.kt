package com.zecdev.starwarspeople

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.zecdev.starwarspeople.model.Character
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharacterTest {
    @Test
    fun unarchive() {
        val dataReceived = "{\n" +
                "    \"count\": 87, \n" +
                "    \"next\": \"https://swapi.co/api/people/?page=2\", \n" +
                "    \"previous\": null, \n" +
                "    \"results\": [\n" +
                "        {\n" +
                "            \"name\": \"Luke Skywalker\", \n" +
                "            \"height\": \"172\", \n" +
                "            \"mass\": \"77\", \n" +
                "            \"hair_color\": \"blond\", \n" +
                "            \"skin_color\": \"fair\", \n" +
                "            \"eye_color\": \"blue\", \n" +
                "            \"birth_year\": \"19BBY\", \n" +
                "            \"gender\": \"male\", \n" +
                "            \"homeworld\": \"https://swapi.co/api/planets/1/\", \n" +
                "            \"films\": [\n" +
                "                \"https://swapi.co/api/films/2/\", \n" +
                "                \"https://swapi.co/api/films/6/\", \n" +
                "                \"https://swapi.co/api/films/3/\", \n" +
                "                \"https://swapi.co/api/films/1/\", \n" +
                "                \"https://swapi.co/api/films/7/\"\n" +
                "            ], \n" +
                "            \"species\": [\n" +
                "                \"https://swapi.co/api/species/1/\"\n" +
                "            ], \n" +
                "            \"vehicles\": [\n" +
                "                \"https://swapi.co/api/vehicles/14/\", \n" +
                "                \"https://swapi.co/api/vehicles/30/\"\n" +
                "            ], \n" +
                "            \"starships\": [\n" +
                "                \"https://swapi.co/api/starships/12/\", \n" +
                "                \"https://swapi.co/api/starships/22/\"\n" +
                "            ], \n" +
                "            \"created\": \"2014-12-09T13:50:51.644000Z\", \n" +
                "            \"edited\": \"2014-12-20T21:17:56.891000Z\", \n" +
                "            \"url\": \"https://swapi.co/api/people/1/\"\n" +
                "        }, \n" +
                "    ]\n" +
                "}"

        val result : HashMap<Int, Character> = Character.unarchive(dataReceived)
        Assert.assertEquals(result.size, 1);
        var character = result.get(1);
        Assert.assertNotNull(character)

        Assert.assertEquals(character!!.skinColor, "fair")
        Assert.assertEquals(character.gender, "male")
        Assert.assertEquals(character.id, 1)
        Assert.assertEquals(character.name, "Luke Skywalker")
        Assert.assertEquals(character.specieId, 1)
        Assert.assertEquals(character.getVehiclesIds().size, 2)
        Assert.assertEquals(character.vehiclesUrls.size, 2)
    }
}
