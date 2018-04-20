package com.isa.ISA;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {

/*
	Metoda vraÄ‡a JSON reprezentaciju prosleÄ‘enog objekta.
*/
	public static String json(Object object)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        
        return mapper.writeValueAsString(object);
    }

}