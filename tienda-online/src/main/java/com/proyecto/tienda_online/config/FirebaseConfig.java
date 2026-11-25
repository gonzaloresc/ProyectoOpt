package com.proyecto.tienda_online.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Value("${firebase.credentials.path:}")  // Busca las credenciales en la ruta dentro de application.properties (los : al final -> si está la ruta vacía credentialsPath será una cadena vacía)
    private String credentialsPath;

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        if (!FirebaseApp.getApps().isEmpty()) {
            return FirebaseApp.getInstance();  // Crea una instancia de FirebaseApp si está vacía para evitar errores "firebaseapp already exists"
        }

        GoogleCredentials credentials;
        if (credentialsPath != null && !credentialsPath.isBlank()) {
            try (InputStream serviceAccount = new FileInputStream(credentialsPath)) {
                credentials = GoogleCredentials.fromStream(serviceAccount);  // Crea las credenciales a partir del json en la ruta
            }
        } else {
            credentials = GoogleCredentials.getApplicationDefault();  // Intentará buscar variables en el entorno
        }
        FirebaseOptions options = FirebaseOptions.builder().setCredentials(credentials).build();  // Prepara la configuración de arranque con las credenciales obtenidas
        return FirebaseApp.initializeApp(options); // Arranca con estas configuraciones
    }

    @Bean
    public FirebaseAuth firebaseAuth(FirebaseApp firebaseApp) {
        return FirebaseAuth.getInstance(firebaseApp);
    }
}
