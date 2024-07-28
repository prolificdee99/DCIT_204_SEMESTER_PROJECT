package app;

public class Main {
    public static Map graph = new Map();

    public static void main(String[] args) {

        Locations mainGate = new Locations("Main Gate");
        Locations UGFireService = new Locations("UG Fire Service");
        Locations greatHall = new Locations("Great Hall");
        Locations nb= new Locations("N Block");
        Locations jqb = new Locations("JQB");
        Locations pentHall = new Locations("Pentagon Hall");
        Locations balmeLibrary = new Locations("Balme Library");
        Locations nnb = new Locations("NNB");
        Locations ugcs = new Locations("UGCS");
        Locations CSDepartment = new Locations("Computer Science Department");
        Locations athleticOval = new Locations("Athletic Oval");
        Locations voltaHall = new Locations("Volta Hall");
        Locations akuafoHall = new Locations("Akuafo Hall");
        Locations legonHall = new Locations("Legon Hall");
        Locations nightMarket = new Locations("Night Market");
        Locations diasporaHalls = new Locations("Diaspora");
        Locations businessSchool = new Locations("Business School");
        Locations lawSchool = new Locations("Law School");
        Locations commonWealthHall = new Locations("Common Wealth Hall");

        graph.addVertex(mainGate);
        graph.addVertex(greatHall);
        graph.addVertex(nb);
        graph.addVertex(UGFireService);
        graph.addVertex(jqb);
        graph.addVertex(pentHall);
        graph.addVertex(balmeLibrary);
        graph.addVertex(nnb);
        graph.addVertex(ugcs);
        graph.addVertex(CSDepartment);
        graph.addVertex(athleticOval);
        graph.addVertex(voltaHall);
        graph.addVertex(akuafoHall);
        graph.addVertex(legonHall);
        graph.addVertex(nightMarket);
        graph.addVertex(diasporaHalls);
        graph.addVertex(businessSchool);
        graph.addVertex(lawSchool);
        graph.addVertex(commonWealthHall);


        graph.addEdge(new Extent(mainGate, jqb, 300));
        graph.addEdge(new Extent(mainGate, UGFireService, 240));
        graph.addEdge(new Extent(mainGate, akuafoHall, 500));

        graph.addEdge(new Extent(UGFireService, akuafoHall, 650));

        graph.addEdge(new Extent(greatHall, commonWealthHall, 100));

        graph.addEdge(new Extent(nb, balmeLibrary, 600));
        graph.addEdge(new Extent(nb, businessSchool, 500));
        graph.addEdge(new Extent(nb, nnb, 500));
        graph.addEdge(new Extent(nb, CSDepartment, 500));
        graph.addEdge(new Extent(nb, ugcs, 500));

        graph.addEdge(new Extent(voltaHall, businessSchool, 200));
        graph.addEdge(new Extent(voltaHall, commonWealthHall,450));
        graph.addEdge(new Extent(voltaHall, legonHall, 190));

        graph.addEdge(new Extent(businessSchool, ugcs, 70));

        graph.addEdge(new Extent(legonHall, akuafoHall, 400));
        graph.addEdge(new Extent(legonHall, athleticOval, 450));
        graph.addEdge(new Extent(legonHall, commonWealthHall, 750));

        graph.addEdge(new Extent(jqb, akuafoHall, 700));
        graph.addEdge(new Extent(jqb, lawSchool, 350));

        graph.addEdge(new Extent(lawSchool, pentHall, 900));

        graph.addEdge(new Extent(ugcs, balmeLibrary, 200));
        graph.addEdge(new Extent(ugcs, voltaHall, 350));

        graph.addEdge(new Extent(CSDepartment, balmeLibrary, 650));
        graph.addEdge(new Extent(CSDepartment, akuafoHall, 850));
        graph.addEdge(new Extent(CSDepartment, lawSchool, 650));

        graph.addEdge(new Extent(balmeLibrary, akuafoHall, 270));
        graph.addEdge(new Extent(balmeLibrary, legonHall, 500));

        graph.addEdge(new Extent(athleticOval, akuafoHall, 550));
        graph.addEdge(new Extent(athleticOval, nightMarket, 800));

        graph.addEdge(new Extent(akuafoHall, nightMarket, 1000));

        graph.addEdge(new Extent(nightMarket, diasporaHalls, 850));

        new UserInterface();

    }
}