package com.assets.SVGAssets;

public class SVGPathElement {

    String content;
    String id;
    String name;

    public SVGPathElement(String content, String id, String name) {
        this.content = content;
        this.id = id;
        this.name = name;
    }

    

    @Override
    public String toString() {
        return "SVGPathElement [content=" + content + ", id=" + id + ", name=" + name + "]";
    }



    public String getContent() {
        return content;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
