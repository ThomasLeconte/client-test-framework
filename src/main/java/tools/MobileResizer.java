package tools;

import pages.AbstractPage;

import java.util.HashMap;
import java.util.Map;

public class MobileResizer {

    Map<String, Dimensions> references;

    public MobileResizer() {
        this.references = new HashMap<>();
    }

    public void addReference(String name, int width, int height) {
        if (!this.references.containsKey(name)) {
            this.references.put(name, new Dimensions(width, height));
        }
    }

    public void resizePage(AbstractPage page, String refName) throws Exception {
        if (references.containsKey(refName)) {
            Dimensions d = references.get(refName);
            page.modifierTailleEcran(d.getWidth(), d.getHeight());
        } else {
            throw new Exception("La référence de téléphone \"" + refName + "\" n'a pas été définie.");
        }
    }

    static class Dimensions {
        private int width;
        private int height;

        public Dimensions(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
