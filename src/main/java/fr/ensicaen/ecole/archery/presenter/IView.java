package fr.ensicaen.ecole.archery.presenter;

import fr.ensicaen.ecole.archery.data.Map;

public interface IView {
    void drawTarget(double centerX, double centerY, double radius, int numberOfZones);
    void drawScoreZone(double width, double height, double x, double y);
    void drawBow(double width, double height, double x, double y, String type);
    void drawNumberArrows(double width, double height, double x, double y);
    
    void setPresenter(GamePresenter gamePresenter);
    void changeScore(int score);
    void changeNumberArrows(int numberArrows);

    void drawPowerBar(double width, double height, double x, double y);
    void updateBow(double angleX, double scaleFactor);
    void updatePowerBar(double power);

    void stopChargingTimer();
    void startChargingTimer();

    void setBowPowerImage(double angle, double chargePower);
    void drawHomeButton();
    void drawRetryButton();
    void setWelcomePageScene();
    void displayGameView(Map map, String bowType, double zoomFactor);
    void displayEndPopUp(int score);

    void drawCircle(double x, double y, double size);
    void deleteTrajectory();
    void setShopPage();
    void changeMoneyValue(int value);
    void displayShopErrorMessage();
    void hideShopErrorMessage();
    void setSoldRogerBow();
    void setSoldZoom();
    void setSoldStabilizer();

}