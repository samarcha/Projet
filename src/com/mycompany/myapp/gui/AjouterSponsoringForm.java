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
import com.mycompany.myapp.entities.Sponsoring;
import com.mycompany.myapp.services.CandidatService;
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
public class AjouterSponsoringForm  extends Form {
        String destinationUrl = "";

    public AjouterSponsoringForm(Form previous) {
        Form current;
        //garder trace de la form en cours  pour la passer en paramétre sur interfaces suivants
//pour pouvoir  y revenir  plus tard  en utilisant la méthode showBack
        current = this;//recupération de l'interface (Form) en cours dans current
        setTitle("Ajouter Publicité ");
        setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        TextField txtDescription = new TextField("", "description");
        TextField txtType = new TextField("", "type");
        Picker date_debut = new Picker();
        Picker date_fin = new Picker();

        ArrayList<Candidat> listCondidat = CandidatService.getInstance().getCandidat();
        ComboBox<String> comboCandidat = new ComboBox<>();
        for (Candidat candidat : listCondidat) {
            comboCandidat.addItem(candidat.getNom());//ajouter des noms dans le combobox
        }
        add(comboCandidat);
        Button testImage = new Button("Browse Images");
        testImage.addActionListener((ActionEvent e)->{
            if (FileChooser.isAvailable()) {
                FileChooser.showOpenDialog(false, ".pdf,application/pdf,.gif,image/gif,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg,.bmp", e2-> {                    
                    if(e2!=null && e2.getSource()!=null) {
                        String path = (String)e2.getSource();
                        destinationUrl = path;
                        System.out.println(path);
                        Random random = new Random();
                        String letters = "abcdefghijklmnopqrstuvwxyz";         
                        StringBuilder randomName = new StringBuilder("") ;
                        for(int i = 0; i < 10; i++) {
                            randomName.append(letters.charAt(random.nextInt(letters.length())));
                        } 
                        //destinationUrl = "src/Images/" + randomName + ".jpg";
                        //Path destinationPath = Paths.get(destinationUrl);
                        //Path sourcePath = Paths.get(path);
                       /* try {
                            Files.copy(sourcePath , destinationPath);
                        } catch (IOException ex) {
                            Logger.getLogger(AjouterSponsoringForm.class.getName()).log(Level.SEVERE, null, ex);
                        }*/
                        try {
                            //Image img = Image.createImage(path);
                            if (true) return;
                        } catch (Exception ex) {
                            Log.e(ex);
                       }
                    }
               });
            }
        });
        
        Button btnAjouter = new Button("Ajouter");
         btnAjouter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if ((txtDescription.getText().length()==0)||(txtType.getText().length()==0)) {
                    Dialog.show("Alert", "Veuillez remplir tous les champs", "ok", null);
                } else {
                    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                    String dateDebut = formater.format(date_debut.getDate());
                    String dateFin = formater.format(date_fin.getDate());
                    Sponsoring sponsoring = new Sponsoring(txtDescription.getText(), txtType.getText(),destinationUrl, 1, listCondidat.get(comboCandidat.getSelectedIndex()).getId(), dateDebut, dateFin);
          
                    ServiceSponsoring.getInstance().addSponsoring(sponsoring);
            new ListeSponsoringForm(previous).showBack();
                }
            }
        });
        addAll(txtDescription,txtType,date_debut,date_fin,testImage,btnAjouter);
  }
}

