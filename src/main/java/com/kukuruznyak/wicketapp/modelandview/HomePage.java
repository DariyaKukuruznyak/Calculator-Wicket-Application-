package com.kukuruznyak.wicketapp.modelandview;

import com.kukuruznyak.wicketapp.controller.HomePageController;
import com.kukuruznyak.wicketapp.modelandview.enums.ArithmeticOperations;
import com.kukuruznyak.wicketapp.modelandview.enums.Numbers;
import com.kukuruznyak.wicketapp.modelandview.enums.OtherOperations;
import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.PropertyModel;

public class HomePage extends WebPage {
    private Label displayLabel;
    private StringBuilder display;
    private HomePageController controller;

    public HomePage(final PageParameters parameters) {
        super(parameters);

        add(CSSPackageResource.getHeaderContribution(HomePage.class, "HomePage.css"));

        Numbers numbers[] = Numbers.values();
        ArithmeticOperations arithmeticOperations[] = ArithmeticOperations.values();
        OtherOperations otherOperations[] = OtherOperations.values();
        controller = new HomePageController();
        for (Numbers number : numbers) {
            add(new AjaxFallbackLink(number.toString()) {
                @Override
                public void onClick(AjaxRequestTarget target) {
                    controller.inputNumber(Numbers.getNumber(this.getId()));
                    target.addComponent(displayLabel);
                }
            });
        }

        for (ArithmeticOperations operation : arithmeticOperations) {
            add(new AjaxFallbackLink(operation.toString()) {
                @Override
                public void onClick(AjaxRequestTarget target) throws IllegalArgumentException {
                    controller.inputArithmeticOperation(ArithmeticOperations.valueOf(this.getId()));
                    target.addComponent(displayLabel);
                }
            });
        }

        for (OtherOperations operation : otherOperations) {
            add(new AjaxFallbackLink(operation.toString()) {
                @Override
                public void onClick(AjaxRequestTarget target) throws IllegalArgumentException {
                    controller.inputOtherOperation(OtherOperations.valueOf(this.getId()));
                    target.addComponent(displayLabel);
                }
            });
        }
        display = controller.getDisplay();
        displayLabel = new Label("displayLabel", new PropertyModel(this, "display"));
        displayLabel.setOutputMarkupId(true);

        add(displayLabel);
    }
}