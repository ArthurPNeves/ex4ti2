package ex4;

import com.microsoft.azure.cognitiveservices.vision.computervision.*;

import com.microsoft.azure.cognitiveservices.vision.computervision.models.*;

import java.util.ArrayList;
import java.util.List;

public class ia{

    private static String chave = "2e3cb9b8ca0648668154b42817016a03";
    
    private static String link = "https://ti2ex4.cognitiveservices.azure.com/";

    public static void main(String[] args) {

        ComputerVisionClient client = authenticate(chave, link); 

        analisarRemoteImage(client);

    }

    public static ComputerVisionClient authenticate(String subscriptionchave, String link){
        return ComputerVisionManager.authenticate(subscriptionchave).withEndpoint(link);
    }


    public static void analisarRemoteImage(ComputerVisionClient client) {
        String img = "https://source.unsplash.com/random/300×300";

        List<VisualFeatureTypes> arrayfe = new ArrayList<>();
        arrayfe.add(VisualFeatureTypes.TAGS);

        for(int i = 0; i < 5; i++) {
            System.out.printf("\n\n %d...\n", i + 1);
            analisar(arrayfe, img, client);
        }
    }

    public static void analisar(List<VisualFeatureTypes> arrayfe, String img, ComputerVisionClient client) {
        try {
            ImageAnalysis analysis = client.computerVision().analyzeImage().withUrl(img)
                    .withVisualFeatures(arrayfe).execute();


            for (ImageTag tag : analysis.tags()) 
                System.out.printf(" %s  %f\n", tag.name(), tag.confidence());
            
        } catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
}
