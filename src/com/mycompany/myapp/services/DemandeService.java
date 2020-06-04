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
import com.mycompany.myapp.entities.DemandeSponsoring;
import com.mycompany.myapp.entities.Sponsoring;

import com.mycompany.myapp.services.utils.source;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Khoubaib
 */
public class DemandeService {
      public ArrayList<DemandeSponsoring> demandeSponsoring;
    
    public static DemandeService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
     public DemandeService() {
         req = new ConnectionRequest();
    }
    
    public static DemandeService getInstance() {
        if (instance == null) {
            instance = new DemandeService();
        }
        return instance;
    }

    public boolean addDemandeSponsoring(DemandeSponsoring t) {
        String url = "http://localhost/untitled12/web/app_dev.php/Demande/ajouter?description=" + t.getDescription() + "&type=" + t.getType()  + "&sponsor_id="+ String.valueOf(t.getSponsor_id()) + "&candidat_id=" + t.getCandidat_id() + "&date=" + t.getDate();
       req.setUrl(url); 
       //cette url sera envoyer à notre requete, la requete aura comme paramétre url avec la méthode setUrl
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;;// reponse Code HTTP 200 :toute est bien passé 
                req.removeResponseListener(this);
        //supprimer notre actionListener,on va pas le garder dans notre requete et on va executer notre requete 
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        //envoyer des requetes au serveur d'une facon asynchrone
        return resultOK;
    }
    
    public ArrayList<DemandeSponsoring> parseDemandeSponsoring(String jsonText){
    //la methode qui fait le parsing des données
    //elle prend à l'entree un text jason et la convertit en liste sponsoring
     
        try {
            demandeSponsoring=new ArrayList<>();
            JSONParser j = new JSONParser();
           //instanciation d'un objet JSONParser permettant le parsing du resultat jason
            Map<String,Object> demandeListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
         //l'objet j a une methode parseJSON qui permet d'extraire les données de notre resultat de serveur
//on doit convertir notre reponse texte  en CharArray afin de permettre au JASONParser de le lire  et la manipuler
            //Format jason impose que l'objet soit definit sous forme de cle et valeur
            //Pour cela, on utilise la structure map pour stocker des couples cle/valeur
            List<Map<String,Object>> list = (List<Map<String,Object>>)demandeListJson.get("root");
    //recuperer la liste sponsoring jason
            for(Map<String,Object> obj : list){
                DemandeSponsoring t = new DemandeSponsoring();
                float id = Float.parseFloat(obj.get("id").toString());
                //puisque qu'on est sure il est un entier on peut plustard forcer son type en entier
                t.setId((int)id);
                t.setDescription(obj.get("description").toString());
                t.setType(obj.get("type").toString());
                t.setCandidat_id(((Double)obj.get("candidatId")).intValue());
                t.setSponsor_id(((Double)obj.get("sponsorId")).intValue());
                t.setDate(obj.get("date").toString());
               
                
                demandeSponsoring.add(t);
            }
            
        } catch (IOException ex) {
       
        }
        return demandeSponsoring;
  
    }
public ArrayList<DemandeSponsoring> getDemandeSponsoring(){
      
        String url = "http://localhost/untitled12/web/app_dev.php/Demande/afficher";
        req.setUrl(url);
          //cette url sera envoyer à notre requete, la requete aura comme paramétre url avec la méthode setUrl
        req.setPost(false);//si elle n'est pas post ,elle sera de type get
        req.addResponseListener(new ActionListener<NetworkEvent>() { //recevoir la reponse
            @Override
            public void actionPerformed(NetworkEvent evt) {
                demandeSponsoring = parseDemandeSponsoring(new String(req.getResponseData())); 
//une fois que nous avons recevoire une reponse de notre serveur,on va recuperer cette reponse en bytechart 
//on va le convertir en string et on va le passer à une methode parseDemandeSponsoring c'est elle qui va se charger de 
//convertir en une liste de demandeSponsoring
                req.removeResponseListener(this);   
  //supprimer notre actionListener,on va pas le garder dans notre requete et on va executer notre requete   
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);  
//envoyer des requetes au serveur d'une facon asynchrone
        return demandeSponsoring;
    }


 public void delete(DemandeSponsoring t) {
        
            String url = "http://localhost/untitled12/web/app_dev.php/Demande/delete/" + t.getId();
            req.setUrl(url);
             //cette url sera envoyer à notre requete, la requete aura comme paramétre url avec la méthode setUrl
            req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    resultOK = req.getResponseCode() == 200; // reponse Code HTTP 200 :toute est bien passé 
                    req.removeResponseListener(this);
//supprimer notre actionListener,on va pas le garder dans notre requete et on va executer notre requete  
                }
            });
            NetworkManager.getInstance().addToQueueAndWait(req);
              //envoyer des requetes au serveur d'une facon asynchrone
        
    }

    
}
