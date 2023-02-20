package Sort;

class Mountain {

        String name;
        int heights;

    public Mountain(String name, int heights) {
        this.name = name;
        this.heights = heights;
    }

    public String getName() {
        return name;
    }

    public int getHeights() {
        return heights;
    }

    @Override
    public String toString() {
        return name + " " + heights;
    }
}
