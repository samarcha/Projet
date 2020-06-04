/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.io.URL;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.mycompany.myapp.entities.Candidat;
import com.mycompany.myapp.entities.DemandeSponsoring;
import com.mycompany.myapp.entities.Sponsoring;
import com.mycompany.myapp.services.CandidatService;
import com.mycompany.myapp.services.DemandeService;
import com.mycompany.myapp.services.ServiceSponsoring;
import java.io.IOException;

import java.util.ArrayList;
import org.littlemonkey.connectivity.Connectivity;

/**
 *
 * @author Khoubaib
 */
public class Accueil extends Form {

    Form current;
 //garder trace de la form en cours  pour la passer en paramétre sur interfaces suivants
//pour pouvoir  y revenir  plus tard  en utilisant la méthode showBack
    public Accueil() {
         try {
           current = this;//recupération de l'interface (Form) en cours dans current
            Tabs tabs = new Tabs();
            Style s = UIManager.getInstance().getComponentStyle("Tab");
            FontImage mus = FontImage.createMaterial(FontImage.MATERIAL_MUSIC_NOTE, s);
            ImageViewer iv = new ImageViewer(Image.createImage("/cc.jpg"));
            iv.setImageInitialPosition(ImageViewer.IMAGE_FIT);
            tabs.addTab("Tunisian Got Talent", mus, iv);
            current.setLayout(new BorderLayout());
            current.add(BorderLayout.CENTER, tabs);
            tabs.getUIID();
           current.show();
        } catch (IOException ex) {}
        getToolbar().addMaterialCommandToSideMenu("Publicités", FontImage.MATERIAL_LIST, e -> {
                if (Connectivity.isConnected()) {
                 new ListeSponsoringForm(current).show();
            } else {
                 Dialog.show("Alert", "Merci de Verifier Votre Connexion Internet", "ok", "");
            }
         
        });
        getToolbar().addMaterialCommandToSideMenu("Candidats", FontImage.MATERIAL_PEOPLE, e -> ShowCandidat(current));
        getToolbar().addMaterialCommandToSideMenu("Demandes", FontImage.MATERIAL_LIST, e -> {
                if (Connectivity.isConnected()) {
                 new ListeDemandeSponsoringForm(current).show();
            } else {
                 Dialog.show("Alert", "Merci de Verifier Votre Connexion Internet", "ok", "");
            }
       });
    
         }
        public void ShowCandidat(Form previous){
           
            Form liste = new Form("Liste Des Candidats", BoxLayout.y());
           liste.getToolbar().setBackCommand("retour", (ev4) -> {
            show();
        });
           
        com.mycompany.myapp.services.CandidatService sp = new CandidatService();
             if (Connectivity.isConnected()) {
            ArrayList<Candidat> listeCandidat = new ArrayList<>();
            listeCandidat = sp.getCandidat();
            for (Candidat P : listeCandidat) {
                Container c = new Container(BoxLayout.y());
                Container c1 = new Container();
                Container c2 = new Container();
                Container c3 = new Container();
                Label nom = new Label("Nom:");
                Label lnom = new Label(P.getNom());
                Label prenom = new Label("Prenom:");
                Label lprenom = new Label(P.getPrenom());
                Label momhj = new Label("  *******   ");
                c1.addAll(nom,lnom);
                c2.addAll(prenom,lprenom);
                c3.add(momhj );
                c.addAll(c1,c2,c3);
                liste.add(c);
                liste.show();
            }
            } else {
                 Dialog.show("Alert", "Merci de verifier votre connexion internet", "ok", "");
            }
        }
        com.mycompany.myapp.services.ServiceSponsoring Sponsoring = new ServiceSponsoring();
    }

