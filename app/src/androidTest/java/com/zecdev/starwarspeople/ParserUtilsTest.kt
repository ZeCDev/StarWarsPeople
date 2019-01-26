package com.zecdev.starwarspeople

import android.support.test.runner.AndroidJUnit4
import com.zecdev.starwarspeople.model.ParserUtils
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ParserUtilsTest {
    @Test
    fun hasNext() {
        val dataReceived1 = "{\n" +
                "    \"count\": 37, \n" +
                "    \"next\": \"https://swapi.co/api/species/?page=3\", \n" +
                "    \"previous\": \"https://swapi.co/api/species/?page=1\", \n" +
                "    \"results\": [\n" +
                "        {\n" +
                "            \"name\": \"Twi'lek\", \n" +
                "            \"classification\": \"mammals\", \n" +
                "            \"designation\": \"sentient\", \n" +
                "            \"average_height\": \"200\", \n" +
                "            \"skin_colors\": \"orange, yellow, blue, green, pink, purple, tan\", \n" +
                "            \"hair_colors\": \"none\", \n" +
                "            \"eye_colors\": \"blue, brown, orange, pink\", \n" +
                "            \"average_lifespan\": \"unknown\", \n" +
                "            \"homeworld\": \"https://swapi.co/api/planets/37/\", \n" +
                "            \"language\": \"Twi'leki\", \n" +
                "            \"people\": [\n" +
                "                \"https://swapi.co/api/people/45/\", \n" +
                "                \"https://swapi.co/api/people/46/\"\n" +
                "            ], \n" +
                "            \"films\": [\n" +
                "                \"https://swapi.co/api/films/5/\", \n" +
                "                \"https://swapi.co/api/films/4/\", \n" +
                "                \"https://swapi.co/api/films/6/\", \n" +
                "                \"https://swapi.co/api/films/3/\"\n" +
                "            ], \n" +
                "            \"created\": \"2014-12-20T09:48:02.406000Z\", \n" +
                "            \"edited\": \"2014-12-20T21:36:42.169000Z\", \n" +
                "            \"url\": \"https://swapi.co/api/species/15/\"\n" +
                "        }, \n" +
                "        {\n" +
                "            \"name\": \"Aleena\", \n" +
                "            \"classification\": \"reptile\", \n" +
                "            \"designation\": \"sentient\", \n" +
                "            \"average_height\": \"80\", \n" +
                "            \"skin_colors\": \"blue, gray\", \n" +
                "            \"hair_colors\": \"none\", \n" +
                "            \"eye_colors\": \"unknown\", \n" +
                "            \"average_lifespan\": \"79\", \n" +
                "            \"homeworld\": \"https://swapi.co/api/planets/38/\", \n" +
                "            \"language\": \"Aleena\", \n" +
                "            \"people\": [\n" +
                "                \"https://swapi.co/api/people/47/\"\n" +
                "            ], \n" +
                "            \"films\": [\n" +
                "                \"https://swapi.co/api/films/4/\"\n" +
                "            ], \n" +
                "            \"created\": \"2014-12-20T09:53:16.481000Z\", \n" +
                "            \"edited\": \"2014-12-20T21:36:42.171000Z\", \n" +
                "            \"url\": \"https://swapi.co/api/species/16/\"\n" +
                "        }, \n" +
                "    ]\n" +
                "}"

        val dataReceived2 = "{\n" +
                "    \"count\": 37, \n" +
                "    \"next\": null, \n" +
                "    \"previous\": \"https://swapi.co/api/species/?page=3\", \n" +
                "    \"results\": [\n" +
                "        {\n" +
                "            \"name\": \"Togruta\", \n" +
                "            \"classification\": \"mammal\", \n" +
                "            \"designation\": \"sentient\", \n" +
                "            \"average_height\": \"180\", \n" +
                "            \"skin_colors\": \"red, white, orange, yellow, green, blue\", \n" +
                "            \"hair_colors\": \"none\", \n" +
                "            \"eye_colors\": \"red, orange, yellow, green, blue, black\", \n" +
                "            \"average_lifespan\": \"94\", \n" +
                "            \"homeworld\": \"https://swapi.co/api/planets/58/\", \n" +
                "            \"language\": \"Togruti\", \n" +
                "            \"people\": [\n" +
                "                \"https://swapi.co/api/people/78/\"\n" +
                "            ], \n" +
                "            \"films\": [\n" +
                "                \"https://swapi.co/api/films/5/\", \n" +
                "                \"https://swapi.co/api/films/6/\"\n" +
                "            ], \n" +
                "            \"created\": \"2014-12-20T18:44:03.246000Z\", \n" +
                "            \"edited\": \"2014-12-20T21:36:42.209000Z\", \n" +
                "            \"url\": \"https://swapi.co/api/species/35/\"\n" +
                "        }, \n" +
                "    ]\n" +
                "}"

        val result1 : Boolean = ParserUtils.hasNext(dataReceived1)
        Assert.assertEquals(result1, true);

        val result2 : Boolean = ParserUtils.hasNext(dataReceived2)
        Assert.assertEquals(result2, false);
    }

    @Test
    fun actualPage() {
        val dataReceived = "{\n" +
                "    \"count\": 37, \n" +
                "    \"next\": \"https://swapi.co/api/species/?page=3\", \n" +
                "    \"previous\": \"https://swapi.co/api/species/?page=1\", \n" +
                "    \"results\": [\n" +
                "        {\n" +
                "            \"name\": \"Twi'lek\", \n" +
                "            \"classification\": \"mammals\", \n" +
                "            \"designation\": \"sentient\", \n" +
                "            \"average_height\": \"200\", \n" +
                "            \"skin_colors\": \"orange, yellow, blue, green, pink, purple, tan\", \n" +
                "            \"hair_colors\": \"none\", \n" +
                "            \"eye_colors\": \"blue, brown, orange, pink\", \n" +
                "            \"average_lifespan\": \"unknown\", \n" +
                "            \"homeworld\": \"https://swapi.co/api/planets/37/\", \n" +
                "            \"language\": \"Twi'leki\", \n" +
                "            \"people\": [\n" +
                "                \"https://swapi.co/api/people/45/\", \n" +
                "                \"https://swapi.co/api/people/46/\"\n" +
                "            ], \n" +
                "            \"films\": [\n" +
                "                \"https://swapi.co/api/films/5/\", \n" +
                "                \"https://swapi.co/api/films/4/\", \n" +
                "                \"https://swapi.co/api/films/6/\", \n" +
                "                \"https://swapi.co/api/films/3/\"\n" +
                "            ], \n" +
                "            \"created\": \"2014-12-20T09:48:02.406000Z\", \n" +
                "            \"edited\": \"2014-12-20T21:36:42.169000Z\", \n" +
                "            \"url\": \"https://swapi.co/api/species/15/\"\n" +
                "        }, \n" +
                "        {\n" +
                "            \"name\": \"Aleena\", \n" +
                "            \"classification\": \"reptile\", \n" +
                "            \"designation\": \"sentient\", \n" +
                "            \"average_height\": \"80\", \n" +
                "            \"skin_colors\": \"blue, gray\", \n" +
                "            \"hair_colors\": \"none\", \n" +
                "            \"eye_colors\": \"unknown\", \n" +
                "            \"average_lifespan\": \"79\", \n" +
                "            \"homeworld\": \"https://swapi.co/api/planets/38/\", \n" +
                "            \"language\": \"Aleena\", \n" +
                "            \"people\": [\n" +
                "                \"https://swapi.co/api/people/47/\"\n" +
                "            ], \n" +
                "            \"films\": [\n" +
                "                \"https://swapi.co/api/films/4/\"\n" +
                "            ], \n" +
                "            \"created\": \"2014-12-20T09:53:16.481000Z\", \n" +
                "            \"edited\": \"2014-12-20T21:36:42.171000Z\", \n" +
                "            \"url\": \"https://swapi.co/api/species/16/\"\n" +
                "        }, \n" +
                "    ]\n" +
                "}"

        val result : Int = ParserUtils.getActualPage(dataReceived)
        Assert.assertEquals(result, 2);
    }

    @Test
    fun nextPage() {
        val dataReceived1 = "{\n" +
                "    \"count\": 37, \n" +
                "    \"next\": \"https://swapi.co/api/species/?page=3\", \n" +
                "    \"previous\": \"https://swapi.co/api/species/?page=1\", \n" +
                "    \"results\": [\n" +
                "        {\n" +
                "            \"name\": \"Twi'lek\", \n" +
                "            \"classification\": \"mammals\", \n" +
                "            \"designation\": \"sentient\", \n" +
                "            \"average_height\": \"200\", \n" +
                "            \"skin_colors\": \"orange, yellow, blue, green, pink, purple, tan\", \n" +
                "            \"hair_colors\": \"none\", \n" +
                "            \"eye_colors\": \"blue, brown, orange, pink\", \n" +
                "            \"average_lifespan\": \"unknown\", \n" +
                "            \"homeworld\": \"https://swapi.co/api/planets/37/\", \n" +
                "            \"language\": \"Twi'leki\", \n" +
                "            \"people\": [\n" +
                "                \"https://swapi.co/api/people/45/\", \n" +
                "                \"https://swapi.co/api/people/46/\"\n" +
                "            ], \n" +
                "            \"films\": [\n" +
                "                \"https://swapi.co/api/films/5/\", \n" +
                "                \"https://swapi.co/api/films/4/\", \n" +
                "                \"https://swapi.co/api/films/6/\", \n" +
                "                \"https://swapi.co/api/films/3/\"\n" +
                "            ], \n" +
                "            \"created\": \"2014-12-20T09:48:02.406000Z\", \n" +
                "            \"edited\": \"2014-12-20T21:36:42.169000Z\", \n" +
                "            \"url\": \"https://swapi.co/api/species/15/\"\n" +
                "        }, \n" +
                "        {\n" +
                "            \"name\": \"Aleena\", \n" +
                "            \"classification\": \"reptile\", \n" +
                "            \"designation\": \"sentient\", \n" +
                "            \"average_height\": \"80\", \n" +
                "            \"skin_colors\": \"blue, gray\", \n" +
                "            \"hair_colors\": \"none\", \n" +
                "            \"eye_colors\": \"unknown\", \n" +
                "            \"average_lifespan\": \"79\", \n" +
                "            \"homeworld\": \"https://swapi.co/api/planets/38/\", \n" +
                "            \"language\": \"Aleena\", \n" +
                "            \"people\": [\n" +
                "                \"https://swapi.co/api/people/47/\"\n" +
                "            ], \n" +
                "            \"films\": [\n" +
                "                \"https://swapi.co/api/films/4/\"\n" +
                "            ], \n" +
                "            \"created\": \"2014-12-20T09:53:16.481000Z\", \n" +
                "            \"edited\": \"2014-12-20T21:36:42.171000Z\", \n" +
                "            \"url\": \"https://swapi.co/api/species/16/\"\n" +
                "        }, \n" +
                "    ]\n" +
                "}"


        val dataReceived2 = "{\n" +
                "    \"count\": 37, \n" +
                "    \"next\": null, \n" +
                "    \"previous\": \"https://swapi.co/api/species/?page=3\", \n" +
                "    \"results\": [\n" +
                "        {\n" +
                "            \"name\": \"Togruta\", \n" +
                "            \"classification\": \"mammal\", \n" +
                "            \"designation\": \"sentient\", \n" +
                "            \"average_height\": \"180\", \n" +
                "            \"skin_colors\": \"red, white, orange, yellow, green, blue\", \n" +
                "            \"hair_colors\": \"none\", \n" +
                "            \"eye_colors\": \"red, orange, yellow, green, blue, black\", \n" +
                "            \"average_lifespan\": \"94\", \n" +
                "            \"homeworld\": \"https://swapi.co/api/planets/58/\", \n" +
                "            \"language\": \"Togruti\", \n" +
                "            \"people\": [\n" +
                "                \"https://swapi.co/api/people/78/\"\n" +
                "            ], \n" +
                "            \"films\": [\n" +
                "                \"https://swapi.co/api/films/5/\", \n" +
                "                \"https://swapi.co/api/films/6/\"\n" +
                "            ], \n" +
                "            \"created\": \"2014-12-20T18:44:03.246000Z\", \n" +
                "            \"edited\": \"2014-12-20T21:36:42.209000Z\", \n" +
                "            \"url\": \"https://swapi.co/api/species/35/\"\n" +
                "        }, \n" +
                "    ]\n" +
                "}"

        val result1 : Int = ParserUtils.getNextPage(dataReceived1)
        Assert.assertEquals(result1, 3);

        val result2 : Int = ParserUtils.getNextPage(dataReceived2)
        Assert.assertEquals(result2, -1);
    }
}
