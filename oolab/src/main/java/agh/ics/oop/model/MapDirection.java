package agh.ics.oop.model;

public enum MapDirection {
    NORTH,
    EAST,
    SOUTH,
    WEST;


    public String toString(){
        return switch(this) {
            case NORTH -> "Północ";
            case SOUTH -> "Południe";
            case WEST -> "Zachód";
            case EAST -> "Wschód";
        };
    }

    public MapDirection next(){
        int totalDirection = MapDirection.values().length;
        int nextDirection = (this.ordinal() + totalDirection + 1) % totalDirection;
        return MapDirection.values()[nextDirection];
    }

    public MapDirection previous(){
        int totalDirection = MapDirection.values().length;
        int previousDirection = (this.ordinal() + totalDirection - 1) % totalDirection;
        return MapDirection.values()[previousDirection];
    }

    public Vector2d toUnitVector(){
        return switch (this){
            case NORTH -> new Vector2d(0, 1);
            case SOUTH -> new Vector2d(0, -1);
            case WEST -> new Vector2d(-1, 0);
            case EAST -> new Vector2d(1, 0);
        };
    }
}
