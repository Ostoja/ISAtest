package com.isa.ISA.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {
	@POST
	@Path("/file/{file}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public String uploadFile(@PathParam("file") String file,@Context HttpServletRequest request,@RequestBody InputStream fileInputStream) throws Exception {	
		System.out.println(file);
		String path = request.getServletContext().getRealPath("");		
		String pathSlike = path+File.separator+file;	
		saveFile(fileInputStream, pathSlike);
		System.out.println("File File"+ pathSlike+ " "+ file);
		return file;   
	}
	
	@GET
	@Path("/loadFile/{file}")
	@Produces("image/png")
	public Response getFullImage(@PathParam("file") String file,@Context HttpServletRequest request) {

		String pom = request.getServletContext().getRealPath("")+File.separator+file;
		System.out.println("LOAD file "+pom);

	    BufferedImage image = null;
		try {
			image = ImageIO.read(new File(pom));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    try{
	    	ImageIO.write(image, "png", baos);
	    }
	    catch(Exception ex)
	    {
	    	System.out.println("Eror");
	    }
	    
	    byte[] imageData = baos.toByteArray();

	    // uncomment line below to send non-streamed
	    return Response.ok(imageData).build();

	    // uncomment line below to send streamed
	    //return Response.ok(new ByteArrayInputStream(imageData)).build();
	}
	
	 private void saveFile(InputStream uploadedInputStream, String serverLocation) {
	     try {
	         OutputStream outpuStream = new FileOutputStream(new File(serverLocation));
	         int read = 0;
	         byte[] bytes = new byte[1024];
	         outpuStream = new FileOutputStream(new File(serverLocation));
	         while ((read = uploadedInputStream.read(bytes)) != -1) {
	             outpuStream.write(bytes, 0, read);
	         }
	         outpuStream.close();
	     } catch (IOException e) {
	         e.printStackTrace();
	
	     }
	 }
}
