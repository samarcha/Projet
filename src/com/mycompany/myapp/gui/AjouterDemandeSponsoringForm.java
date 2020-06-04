/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;


//import com.codename1.components.Switch;
import com.codename1.components.MultiButton;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.Log;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Candidat;
import com.mycompany.myapp.entities.DemandeSponsoring;
import com.mycompany.myapp.entities.Sponsoring;
import com.mycompany.myapp.services.CandidatService;
import com.mycompany.myapp.services.DemandeService;
import com.mycompany.myapp.services.ServiceSponsoring;
import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.scene.control.Alert;


/**
 *
 * @author Khoubaib
 */
public class AjouterDemandeSponsoringForm  extends Form {
      public static int id;

    public AjouterDemandeSponsoringForm(Form previous) {
        Form current;
        //garder trace de la form en cours  pour la passer en paramétre sur interfaces suivants
//pour pouvoir  y revenir  plus tard  en utilisant la méthode showBack
        current = this;//recupération de l'interface (Form) en cours dans current
        setTitle("Envoyer Demande ");
        setLayout(BoxLayout.y());
       
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        TextField txtDescription = new TextField("", "description");
        TextField txtType = new TextField("", "type");
        Picker date = new Picker();
        

        ArrayList<Candidat> listCondidat = CandidatService.getInstance().getCandidat();
        ComboBox<String> comboCandidat = new ComboBox<>();
        for (Candidat candidat : listCondidat) {
            comboCandidat.addItem(candidat.getNom());
        }
        add(comboCandidat);
       
        
        
        Button btnAjouter = new Button("Envoyer");
         btnAjouter.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent evt) {

                if ((txtDescription.getText().length() == 0) || (txtType.getText().length() == 0)) {
                    Dialog.show("Alert", "Veuillez remplir tous les champs", "ok", null);
                } else {
                    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

                    String Date = formater.format(date.getDate());

                      DemandeSponsoring demandeSponsoring = new DemandeSponsoring(id,txtDescription.getText(), txtType.getText() ,0 , Date ,1, listCondidat.get(comboCandidat.getSelectedIndex()).getId());
                      DemandeService.getInstance().addDemandeSponsoring(demandeSponsoring);
                    Dialog.show("Alert", "Votre demande a été envoyer", "ok", null);
                    new ListeDemandeSponsoringForm(previous).showBack();

                }
            }
        });

        addAll(txtDescription, txtType, date, btnAjouter);
    
    }

}
