/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Candidat;
import com.mycompany.myapp.entities.Sponsoring;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Khoubaib
 */
public class CandidatService {
    
 public ArrayList<Candidat> candidat;
    
    public static CandidatService instance=null;
    //on declare un objet instance ayant un type de meme nom que la classe
    public boolean resultOK;
    private ConnectionRequest req;
     public CandidatService() {
 //la creation de l'objet connection request se fait dans le constructeur qui est une accés limitée
         req = new ConnectionRequest();
    }
    
    public static CandidatService getInstance() {
//la methode get instance permet de verifier si l'instance est null, on va appeller au constructeur pour creer l'instance le constructeur qui est une accés limitée
        if (instance == null) {
            instance = new CandidatService();
        }
        return instance;
    }

    
    public ArrayList<Candidat> parseCandidat(String jsonText){
     //la methode qui fait le parsing des données
    //elle prend à l'entree un text jason et la convertit en liste sponsoring
        try {
            candidat=new ArrayList<Candidat>();
            JSONParser j = new JSONParser();
            //instanciation d'un objet JSONParser permettant le parsing du resultat jason
            Map<String,Object> candidatListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
              //l'objet j a une methode parseJSON qui permet d'extraire les données de notre resultat de serveur
//on doit convertir notre reponse texte  en CharArray afin de permettre au JASONParser de le lire  et la manipuler
            //Format jason impose que l'objet soit definit sous forme de cle et valeur
            //Pour cela, on utilise la structure map pour stocker des couples cle/valeur
            List<Map<String,Object>> list = (List<Map<String,Object>>)candidatListJson.get("root");
    //recuperer la liste sponsoring jason
            for(Map<String,Object> obj : list){
            
                Candidat t = new Candidat();
                float id = Float.parseFloat(obj.get("id").toString());
                //puisque qu'on est sure il est un entier on peut plustard forcer son type en entier
                t.setId((int)id);
                t.setNom(obj.get("nom").toString());
                t.setPrenom(obj.get("prenom").toString());
                candidat.add(t);
            }
            
        } catch (IOException ex) {
       
        }
        return candidat;
  
    }
public ArrayList<Candidat> getCandidat(){
       
        String url = "http://localhost/untitled12/web/app_dev.php/Publicite/all";
        req.setUrl(url);
        //cette url sera envoyer à notre requete, la requete aura comme paramétre url avec la méthode setUrl
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() { //recevoir la reponse
            @Override
            public void actionPerformed(NetworkEvent evt) {
                candidat = parseCandidat(new String(req.getResponseData()));
//une fois que nous avons recevoire une reponse de notre serveur,on va recuperer cette reponse en bytechart 
//on va le convertir en string et on va le passer à une methode parseSponsoring c'est elle qui va se charger de 
//convertir en une liste de sponsoring
                req.removeResponseListener(this); 
    //supprimer notre actionListener,on va pas le garder dans notre requete et on va executer notre requete
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req); 
        //envoyer des requetes au serveur d'une facon asynchrone
        return candidat;
    }
}

