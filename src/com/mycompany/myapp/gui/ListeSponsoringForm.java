/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Sponsoring;
import com.mycompany.myapp.services.ServiceSponsoring;
import java.util.ArrayList;

/**
 *
 * @author Khoubaib
 */
public class ListeSponsoringForm extends Form {

    public ListeSponsoringForm() {
    }

    public ListeSponsoringForm(Form previous) {
        Form current;//recupération de l'interface (Form) en cours dans current
 //garder trace de la form en cours  pour la passer en paramétre sur interfaces suivants
//pour pouvoir  y revenir  plus tard  en utilisant la méthode showBack
        current = this;//recupération de l'interface (Form) en cours dans current
        setTitle("Liste des Publicités ");
        setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new Accueil().showBack());
        com.mycompany.myapp.services.ServiceSponsoring Sponsoring = new ServiceSponsoring();
        ArrayList<Sponsoring> listSponsoring = new ArrayList();

        listSponsoring = Sponsoring.getSponsoring();
        for (Sponsoring S : listSponsoring) {
            Container c = new Container(BoxLayout.y());
            Label mo = new Label("Description:");
            Label description = new Label(S.getDescription());
            Label typ = new Label("Type:");
            Label type = new Label(S.getType());
            Label mok = new Label("Date_Debut:");
            Label date_debut = new Label(S.getDate_debut());
            Label mom = new Label("Date_Fin:");
            Label date_fin = new Label(S.getDate_fin());
            Label momhj = new Label("  *******   ");
            description.addPointerPressedListener((evt3) -> {
                new SponsoringDetails(S).show();

            });
            Container c1 = new Container(BoxLayout.x());
            Container c2 = new Container(BoxLayout.x());
            Container c3 = new Container(BoxLayout.x());
            Container c4 = new Container(BoxLayout.x());
            Container c5 = new Container(BoxLayout.x());
            c1.addAll(mo, description);
            c2.addAll(typ, type);
            c3.addAll(mok, date_debut);
            c4.addAll(mom, date_fin);
            c5.add(momhj);
            c.add(c1);
            c.add(c2);
            c.add(c3);
            c.add(c4);
            c.add(c5);
            c.setLeadComponent(description);
            add(c);
            current.show();
        }
        Button btnAjouter = new Button("Ajouter");
        add(btnAjouter);
        show();
        btnAjouter.addActionListener(e -> new AjouterSponsoringForm(current).show());
    }

}
