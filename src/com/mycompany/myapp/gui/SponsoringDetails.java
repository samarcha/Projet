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
import com.mycompany.myapp.entities.Sponsoring;
import com.mycompany.myapp.services.ServiceSponsoring;
import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;

/**
 * GUI builder created Form
 *
 * @author Khoubaib
 */
public class SponsoringDetails extends Form {

    public SponsoringDetails() {}
     
    public SponsoringDetails(Sponsoring sponsoring) {
      Form current;
     
 //garder trace de la form en cours  pour la passer en paramétre sur interfaces suivants
//pour pouvoir  y revenir  plus tard  en utilisant la méthode showBack
      current = this;//recupération de l'interface (Form) en cours dans current
      setTitle("Détails ");
      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e ->{
      new ListeSponsoringForm(current).showBack();}
      );
        Label descriptionDetails = new Label(sponsoring.getDescription());
        final EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(1500, 1100, 0xffff0000), true);
        Image image1 = URLImage.createToStorage(placeholder,
                 sponsoring.getImage(),//key de l'image
                 sponsoring.getImage(),//url de l'image stockée dans la memoire vive
                URLImage.RESIZE_SCALE_TO_FILL);//facon d'afficher l'image dans imageviewer

        ImageViewer imageView = new ImageViewer(image1);
        Button btnSupprimer = new Button("supprimer publicité");
        btnSupprimer.addActionListener((evt) -> {
            ServiceSponsoring.getInstance().deleteSponsoring(sponsoring);
            new ListeSponsoringForm(this).showBack();
        });
      
        add(imageView);
        add(descriptionDetails);
        add(btnSupprimer);
        Button btnModifier = new Button("modifier publicité");
        add(btnModifier);
        show();
        btnModifier.addActionListener(e -> new ModifierSponsoringForm(current, sponsoring).show());

    }
}
