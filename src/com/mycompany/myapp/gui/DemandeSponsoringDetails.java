/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.mycompany.myapp.entities.DemandeSponsoring;
import com.mycompany.myapp.entities.Sponsoring;
import com.mycompany.myapp.services.DemandeService;
import com.mycompany.myapp.services.ServiceSponsoring;
import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;

/**
 * GUI builder created Form
 *
 * @author Khoubaib
 */
public class DemandeSponsoringDetails extends Form{

    public DemandeSponsoringDetails() {}
     
    public DemandeSponsoringDetails(DemandeSponsoring demandeSponsoring) {
      Form current;
//garder trace de la form en cours  pour la passer en paramétre sur interfaces suivants
//pour pouvoir  y revenir  plus tard  en utilisant la méthode showBack
      current = this;//recupération de l'interface (Form) en cours dans current
      setTitle("Demande Sponsoring ");
        //new Accueil().show();
      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e ->{
      new ListeDemandeSponsoringForm(current).showBack();}
      );
        Label descriptionDetails = new Label(demandeSponsoring.getDescription());
       
        Button btnSupprimer = new Button("supprimer demande");
        btnSupprimer.addActionListener((evt) -> {
            DemandeService.getInstance().delete(demandeSponsoring);
            new ListeDemandeSponsoringForm(this).showBack();
        });
      
       
        add(descriptionDetails);
        add(btnSupprimer);
        show();
       

    }
}


    

