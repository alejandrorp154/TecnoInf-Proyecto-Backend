package com.javaee.pryectoBack.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.javaee.pryectoBack.data.ControladorDALocal;
import com.javaee.pryectoBack.model.Usuario;

@Stateless
@Remote(ControladorRemote.class)
public class Controlador implements ControladorRemote, ControladorLocal {

	@EJB
	private ControladorDALocal controladorDA;

	@Override
	public List<Usuario> getUsuarios(int offset, int size) {
		return this.controladorDA.getUsuarios(offset, size);
	}

	@Override
	public void addUsuario(Usuario usuario) {
		this.controladorDA.addUsuario(usuario);
		
	}

	@Override
	public Usuario getUsuario(int idUsuario) throws Exception {
		Usuario res = this.controladorDA.getUsuario(idUsuario);
		if (res != null) {
			return res;
		}
		throw new Exception("No existe Usuario con id=" + idUsuario);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void sendNotification(String message)
	{
		try 
		{
			 FirebaseApp.getInstance();
		}
		catch (IllegalStateException exception)
		{
			FileInputStream serviceAccount;
			try {
				serviceAccount = new FileInputStream("C:\\Users\\palob\\Desktop\\accountFB\\testing-e3324-firebase-adminsdk-8398s-1c3ac239d4.json");

				FirebaseOptions options = new FirebaseOptions.Builder()
				  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
				  .build();

				FirebaseApp.initializeApp(options);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		
		// The topic name can be optionally prefixed with "/topics/".
		String topic = "highScores";

		// See documentation on defining a message payload.
		Message mensaje = Message.builder()
		    .putData("score", "850")
		    .putData("time", "2:45")
		    .setTopic(topic)
		    .build();

		try {
			// Send a message to the devices subscribed to the provided topic.
			String response = FirebaseMessaging.getInstance().send(mensaje);
			// Response is a message ID string.
			System.out.println("Successfully sent message: " + response);
		} catch (FirebaseMessagingException e) {
			e.printStackTrace();
		}
	}
}
