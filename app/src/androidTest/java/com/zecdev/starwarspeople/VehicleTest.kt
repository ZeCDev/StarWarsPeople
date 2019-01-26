package com.zecdev.starwarspeople

import android.support.test.runner.AndroidJUnit4
import com.zecdev.starwarspeople.model.Vehicle
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VehicleTest {
    @Test
    fun unarchive() {
        val dataReceived = "{\n" +
                "    \"count\": 39, \n" +
                "    \"next\": \"https://swapi.co/api/vehicles/?page=2\", \n" +
                "    \"previous\": null, \n" +
                "    \"results\": [\n" +
                "        {\n" +
                "            \"name\": \"Sand Crawler\", \n" +
                "            \"model\": \"Digger Crawler\", \n" +
                "            \"manufacturer\": \"Corellia Mining Corporation\", \n" +
                "            \"cost_in_credits\": \"150000\", \n" +
                "            \"length\": \"36.8\", \n" +
                "            \"max_atmosphering_speed\": \"30\", \n" +
                "            \"crew\": \"46\", \n" +
                "            \"passengers\": \"30\", \n" +
                "            \"cargo_capacity\": \"50000\", \n" +
                "            \"consumables\": \"2 months\", \n" +
                "            \"vehicle_class\": \"wheeled\", \n" +
                "            \"pilots\": [], \n" +
                "            \"films\": [\n" +
                "                \"https://swapi.co/api/films/5/\", \n" +
                "                \"https://swapi.co/api/films/1/\"\n" +
                "            ], \n" +
                "            \"created\": \"2014-12-10T15:36:25.724000Z\", \n" +
                "            \"edited\": \"2014-12-22T18:21:15.523587Z\", \n" +
                "            \"url\": \"https://swapi.co/api/vehicles/4/\"\n" +
                "        }, \n" +
                "    ]\n" +
                "}"

        val result : HashMap<Int, Vehicle> = Vehicle.unarchive(dataReceived)
        Assert.assertEquals(result.size, 1);
        var vehicle = result.get(4);
        Assert.assertNotNull(vehicle)

        Assert.assertEquals(vehicle!!.manufacturer, "Corellia Mining Corporation")
        Assert.assertEquals(vehicle.model, "Digger Crawler")
        Assert.assertEquals(vehicle.id, 4)
        Assert.assertEquals(vehicle.name, "Sand Crawler")
    }
}
