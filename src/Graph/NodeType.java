package Graph;

import java.awt.*;

public enum NodeType {

    EMPTY(new Color(255, 255, 255)),
    WALL(new Color(23,1,4)),
    START(new Color(0, 158, 42)),
    END(new Color(158,0,0)),
    PATHWAY(new Color(239, 182, 42)),
    VISITED(new Color(50, 236, 193)),
    NEW(new Color(236, 149, 50));

    NodeType(Color color) {
        this.color = color;
    }
    private final Color color;

    public Color getColor() {
        return color;
    }
}
