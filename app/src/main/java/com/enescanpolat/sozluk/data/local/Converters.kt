package com.enescanpolat.sozluk.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.enescanpolat.sozluk.domain.model.Meaning
import com.enescanpolat.sozluk.util.GsonParser
import com.enescanpolat.sozluk.util.JsonParser
import com.google.gson.reflect.TypeToken


@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser

) {
    @TypeConverter
    fun fromMeaningsJson(json:String):List<Meaning>{
        return jsonParser.fromJson<ArrayList<Meaning>>(
            json,
            object :TypeToken<ArrayList<Meaning>>(){}.type
        )?: emptyList()
    }

    @TypeConverter
    fun toMeaningsJson(meanings:List<Meaning>):String{
        return jsonParser.toJson(
            meanings,
            object :TypeToken<ArrayList<Meaning>>(){}.type
        )?:"[]"
    }
}