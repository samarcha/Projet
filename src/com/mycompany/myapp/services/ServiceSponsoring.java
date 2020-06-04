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
import com.mycompany.myapp.entities.Sponsoring;
import com.mycompany.myapp.entities.SponsoringModel;
import com.mycompany.myapp.services.utils.source;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Khoubaib
 */
public class ServiceSponsoring {
    public ArrayList<Sponsoring> sponsoring;

    public static ServiceSponsoring instance = null;
    //on declare un objet instance ayant un type de meme nom que la  classe
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceSponsoring() {
   //la creation de l'objet connection request se fait dans le constructeur qui est une accés limitée
        req = new ConnectionRequest();
    }
    
    public static ServiceSponsoring getInstance() {
//la methode get instance permet de verifier si l'instance est null, on va appeller au constructeur pour creer l'instance
        if (instance == null) {
            instance = new ServiceSponsoring();
        }
        return instance;
    }

    public boolean addSponsoring(Sponsoring t) {
        //creation de l'url
        String url = "http://localhost/untitled12/web/app_dev.php/Publicite/ajout?description=" + t.getDescription() + "&type=" + t.getType() + "&image=" + t.getImage() + "&sponsor_id=" + String.valueOf(t.getSponsor_id()) + "&candidat_id=" + t.getCandidat_id() + "&date_debut=" + t.getDate_debut() + "&date_fin=" + t.getDate_fin();
//cette url sera envoyer à notre requete, la requete aura comme paramétre url avec la méthode setUrl
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; // reponse Code HTTP 200 :toute est bien passé 
                //le http fonctionne avec des code de réponse : chaq reponse a son code
                //le code 404 c'est un code http pour dire que la page est NOT FOUND
                req.removeResponseListener(this);
        //supprimer notre actionListener,on va pas le garder dans notre requete et on va executer notre requete  
            }
        });
        //envoyer des requetes au serveur d'une facon asynchrone
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
    public ArrayList<Sponsoring> parseSponsoring(String jsonText){
     //la methode qui fait le parsing des données
    //elle prend à l'entree un text jason et la convertit en liste sponsoring
        try {
     
            sponsoring=new ArrayList<>();
            //instanciation d'un objet JSONParser permettant le parsing du resultat jason
            JSONParser j = new JSONParser();
            Map<String,Object> sponsoringListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//l'objet j a une methode parseJSON qui permet d'extraire les données de notre resultat de serveur
//on doit convertir notre reponse texte  en CharArray afin de permettre au JASONParser de le lire  et la manipuler
            //Format jason impose que l'objet soit definit sous forme de cle et valeur
            //Pour cela, on utilise la structure map pour stocker des couples cle/valeur
         
            List<Map<String,Object>> list = (List<Map<String,Object>>)sponsoringListJson.get("root");
        //recuperer la liste sponsoring jason
            for(Map<String,Object> obj : list){
            
                Sponsoring t = new Sponsoring();
                float id = Float.parseFloat(obj.get("id").toString());//recuperer l'id et la convertir en string il est de type object puis on a convertir en float et 
                //puisque qu'on est sure il est un entier on peut plustard forcer son type en entier
                t.setId((int)id);
                t.setDescription(obj.get("description").toString());
                t.setType(obj.get("type").toString());
                t.setImage(obj.get("image").toString());
                t.setCandidat_id(((Double)obj.get("candidatId")).intValue());
                t.setSponsor_id(((Double)obj.get("sponsorId")).intValue());
                t.setDate_debut(obj.get("dateDebut").toString());
                t.setDate_fin(obj.get("dateFin").toString());
                sponsoring.add(t);
            }
            
        } catch (IOException ex) {
       
        }
        return sponsoring;
  
    }
public ArrayList<Sponsoring> getSponsoring(){
      
        String url = "http://localhost/untitled12/web/app_dev.php/Publicite/list";
        //cette url sera envoyer à notre requete, la requete aura comme paramétre url avec la méthode setUrl
        req.setUrl(url);
        req.setPost(false);//si elle n'est pas post ,elle sera de type get
        req.addResponseListener(new ActionListener<NetworkEvent>() { 
            @Override
            public void actionPerformed(NetworkEvent evt) {
            sponsoring = parseSponsoring(new String(req.getResponseData()));
//une fois que nous avons recevoire une reponse de notre serveur,on va recuperer cette reponse en bytechart 
//on va le convertir en string et on va le passer à une methode parseSponsoring c'est elle qui va se charger de 
//convertir en une liste de sponsoring
                req.removeResponseListener(this);   
//supprimer notre actionListener,on va pas le garder dans notre requete et on va executer notre requete                           
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);  
//envoyer des requetes au serveur d'une facon asynchrone
        return sponsoring;
    }


 public void deleteSponsoring(Sponsoring t) {
        
            String url = "http://localhost/untitled12/web/app_dev.php/Publicite/delete/" + t.getId();
            req.setUrl(url);
            //cette url sera envoyer à notre requete, la requete aura comme paramétre url avec la méthode setUrl
            req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    resultOK = req.getResponseCode() == 200;// reponse Code HTTP 200 :toute est bien passé 
                    req.removeResponseListener(this);
//supprimer notre actionListener,on va pas le garder dans notre requete et on va executer notre requete  
                }
            });
            NetworkManager.getInstance().addToQueueAndWait(req);
             //envoyer des requetes au serveur d'une facon asynchrone
        
    }
 public Boolean updateSponsoring(Sponsoring t){
     String url = "http://localhost/untitled12/web/app_dev.php/Publicite/modifier/" + t.getId()+ "?description=" + t.getDescription() + "&type=" + t.getType() + "&image=" + t.getImage() + "&sponsor_id="+ String.valueOf(t.getSponsor_id()) + "&candidat_id=" + t.getCandidat_id() + "&date_debut=" + t.getDate_debut()+ "&date_fin=" + t.getDate_fin();
     System.out.println(url);    
     req.setPost(false);//si elle n'est pas post ,elle sera de type get
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
             return resultOK;  
 }

}
