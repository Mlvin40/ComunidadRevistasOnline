/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipc2.revistas.digitales.api;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author melvin
 */
@ApplicationPath("v1")
public class RestConfiguration extends ResourceConfig{
    
     public RestConfiguration()  {
        packages("com.ipc2.revistas.digitales.api").register(MultiPartFeature.class);
    }   
}
