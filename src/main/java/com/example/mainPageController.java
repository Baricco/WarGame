package com.example;

import java.io.IOException;
import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.SVGPath;

public class mainPageController {
    
    @FXML
    private Pane mapContainer;
  
  
    @FXML
    private void initialize() {

        // AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        final String svgData1 = "M55,27.5C55,12.337,42.663,0,27.5,0S0,12.337,0,27.5c0,8.009,3.444,15.228,8.926,20.258l-0.026,0.023l0.892,0.752c0.058,0.049,0.121,0.089,0.179,0.137c0.474,0.393,0.965,0.766,1.465,1.127c0.162,0.117,0.324,0.234,0.489,0.348c0.534,0.368,1.082,0.717,1.642,1.048c0.122,0.072,0.245,0.142,0.368,0.212c0.613,0.349,1.239,0.678,1.88,0.98c0.047,0.022,0.095,0.042,0.142,0.064c2.089,0.971,4.319,1.684,6.651,2.105c0.061,0.011,0.122,0.022,0.184,0.033c0.724,0.125,1.456,0.225,2.197,0.292c0.09,0.008,0.18,0.013,0.271,0.021C25.998,54.961,26.744,55,27.5,55c0.749,0,1.488-0.039,2.222-0.098c0.093-0.008,0.186-0.013,0.279-0.021c0.735-0.067,1.461-0.164,2.178-0.287c0.062-0.011,0.125-0.022,0.187-0.034c2.297-0.412,4.495-1.109,6.557-2.055c0.076-0.035,0.153-0.068,0.229-0.104c0.617-0.29,1.22-0.603,1.811-0.936c0.147-0.083,0.293-0.167,0.439-0.253c0.538-0.317,1.067-0.648,1.581-1c0.185-0.126,0.366-0.259,0.549-0.391c0.439-0.316,0.87-0.642,1.289-0.983c0.093-0.075,0.193-0.14,0.284-0.217l0.915-0.764l-0.027-0.023C51.523,42.802,55,35.55,55,27.5z M2,27.5C2,13.439,13.439,2,27.5,2S53,13.439,53,27.5c0,7.577-3.325,14.389-8.589,19.063c-0.294-0.203-0.59-0.385-0.893-0.537l-8.467-4.233c-0.76-0.38-1.232-1.144-1.232-1.993v-2.957c0.196-0.242,0.403-0.516,0.617-0.817c1.096-1.548,1.975-3.27,2.616-5.123c1.267-0.602,2.085-1.864,2.085-3.289v-3.545c0-0.867-0.318-1.708-0.887-2.369v-4.667c0.052-0.519,0.236-3.448-1.883-5.864C34.524,9.065,31.541,8,27.5,8s-7.024,1.065-8.867,3.168c-2.119,2.416-1.935,5.345-1.883,5.864v4.667c-0.568,0.661-0.887,1.502-0.887,2.369v3.545c0,1.101,0.494,2.128,1.34,2.821c0.81,3.173,2.477,5.575,3.093,6.389v2.894c0,0.816-0.445,1.566-1.162,1.958l-7.907,4.313c-0.252,0.137-0.502,0.297-0.752,0.476C5.276,41.792,2,35.022,2,27.5z M42.459,48.132c-0.35,0.254-0.706,0.5-1.067,0.735c-0.166,0.108-0.331,0.216-0.5,0.321c-0.472,0.292-0.952,0.57-1.442,0.83c-0.108,0.057-0.217,0.111-0.326,0.167c-1.126,0.577-2.291,1.073-3.488,1.476c-0.042,0.014-0.084,0.029-0.127,0.043c-0.627,0.208-1.262,0.393-1.904,0.552c-0.002,0-0.004,0.001-0.006,0.001c-0.648,0.16-1.304,0.293-1.964,0.402c-0.018,0.003-0.036,0.007-0.054,0.01c-0.621,0.101-1.247,0.174-1.875,0.229c-0.111,0.01-0.222,0.017-0.334,0.025C28.751,52.97,28.127,53,27.5,53c-0.634,0-1.266-0.031-1.895-0.078c-0.109-0.008-0.218-0.015-0.326-0.025c-0.634-0.056-1.265-0.131-1.89-0.233c-0.028-0.005-0.056-0.01-0.084-0.015c-1.322-0.221-2.623-0.546-3.89-0.971c-0.039-0.013-0.079-0.027-0.118-0.04c-0.629-0.214-1.251-0.451-1.862-0.713c-0.004-0.002-0.009-0.004-0.013-0.006c-0.578-0.249-1.145-0.525-1.705-0.816c-0.073-0.038-0.147-0.074-0.219-0.113c-0.511-0.273-1.011-0.568-1.504-0.876c-0.146-0.092-0.291-0.185-0.435-0.279c-0.454-0.297-0.902-0.606-1.338-0.933c-0.045-0.034-0.088-0.07-0.133-0.104c0.032-0.018,0.064-0.036,0.096-0.054l7.907-4.313c1.36-0.742,2.205-2.165,2.205-3.714l-0.001-3.602l-0.23-0.278c-0.022-0.025-2.184-2.655-3.001-6.216l-0.091-0.396l-0.341-0.221c-0.481-0.311-0.769-0.831-0.769-1.392v-3.545c0-0.465,0.197-0.898,0.557-1.223l0.33-0.298v-5.57l-0.009-0.131c-0.003-0.024-0.298-2.429,1.396-4.36C21.583,10.837,24.061,10,27.5,10c3.426,0,5.896,0.83,7.346,2.466c1.692,1.911,1.415,4.361,1.413,4.381l-0.009,5.701l0.33,0.298c0.359,0.324,0.557,0.758,0.557,1.223v3.545c0,0.713-0.485,1.36-1.181,1.575l-0.497,0.153l-0.16,0.495c-0.59,1.833-1.43,3.526-2.496,5.032c-0.262,0.37-0.517,0.698-0.736,0.949l-0.248,0.283V39.8c0,1.612,0.896,3.062,2.338,3.782l8.467,4.233c0.054,0.027,0.107,0.055,0.16,0.083C42.677,47.979,42.567,48.054,42.459,48.132z";
        final String svgData2 = "m435.710938 117.226562-6.925782-4 12-20.796874 6.925782 4 12-20.800782c3.1875-5.515625 4.050781-12.074218 2.398437-18.226562-1.648437-6.152344-5.679687-11.394532-11.199219-14.574219l-13.863281-8c-5.515625-3.1875-12.070313-4.050781-18.222656-2.398437-6.152344 1.652343-11.394531 5.679687-14.578125 11.199218l-12 20.800782 6.929687 4-12 20.796874-6.929687-4-12.246094 21.28125v-106.296874h-320c-26.5.027343-47.9726562 21.503906-48 48v352c.0273438 26.5 21.5 47.972656 48 48h320v-213.703126l59.710938-103.421874 13.855468 8-24 41.597656 13.859375 8 32-55.421875zm-6.640626-68.503906 13.859376 8c1.835937 1.0625 3.179687 2.8125 3.730468 4.863282.546875 2.050781.257813 4.234374-.804687 6.074218l-4 6.929688-27.710938-16 4-6.929688c2.207031-3.828125 7.097657-5.144531 10.925781-2.9375zm-14.613281 28.519532 12.472657 7.203124-12 20.796876-13.859376-8 12-20.796876zm-366.457031-61.03125h304v24h-312v16h312v24h-304c-17.671875 0-32-14.324219-32-32 0-17.671876 14.328125-32 32-32zm304 416h-296v-296h-16v294.863281c-14.085938-3.636719-23.945312-16.316407-24-30.863281v-316.320313c6.738281 6.0625 15.0625 10.085937 24 11.601563v24.71875h16v-24h296v38.007812l-116.65625 202.058594-4.289062 71.429687 59.714843-39.429687 61.230469-106.058594zm-101.039062-89.09375 26.078124 15.054687-28.09375 18.542969zm34.894531 1.664062-6.925781-4 108-187.058594-13.859376-8-108 187.058594-3.460937-2-3.464844-2 128-221.695312 27.710938 16zm0 0";

        Region region1 = createRegionFromSvg(svgData1);
        Region region2 = createRegionFromSvg(svgData2);

        // AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA

        // Add the SVGPaths as children to the mapContainer Pane
        mapContainer.getChildren().addAll(region1, region2);


    }


    private static Region createRegionFromSvg(String svgData) {
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(svgData);
    
        Region svgRegion = new Region();
        svgRegion.setShape(svgPath);


        svgRegion.setStyle("-fx-background-color: red");
        svgRegion.setMinSize(100, 100); // Imposta le dimensioni minime
        svgRegion.setPrefSize(200, 200); // Imposta le dimensioni preferite
        return svgRegion;
    }

    @FXML
    private void switchToSecondary() throws IOException {
        System.out.println("Hai premuto il pulsantozzo");
    }

}
