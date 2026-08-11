package my.integration.zoho.mapping;

import java.util.Arrays;
import java.util.List;

public enum StoreInfoMapping {

    GC04("GC04","BEDFORD CENTRE CNR SMITH&KIRKBY RDS"),
    GC11("GC11","LONEHILL BOULEVARD LONEHILL"),
    GC12("GC12","ROSEBANK MALL BAKER STREET"),
    GC13("GC13","BENMORE CENTRE C/R WEST&BENMORE STS"),
    GC15("GC15","KILLARNEY MALL RIVIERA ROAD"),
    GC18("GC18","C/R JOHN VORSTER & FERERO STS"),
    GC20("GC20","C/O BOWLING&KELVIN ROADS MORNING GLEN"),
    //GC22("GC22","CNR VANDALAN RD AND PETER RD HENDRIK POTGIETER BOULEVARD"),
    GC28("GC28","C/R JAN SMUTS AVENUE SIXTH ROAD"),
    GC29("GC29","C/R RUSTENBURG ROAD SECOND AVENUE"),
    //GC38("GC38","EXTENTION 36 SOUTH ROAD"),
    GC60("GC60","CEDAR SQUARE MALL CEDAR ROAD"),
    GC64("GC64","CORNER WILLIAM NICOL AND REPUBLIC ROAD"),
    GC68("GC68","HENDRIK POTGIETER ROAD WILGEHEUWEL"),
    GC76("GC76","BARBEQUE DOWNS"),
    GC79("GC79","CORNER OF BROADACRES & WILLIAM NICOL DRIVE"),
    //GC85("GC85","Halfway House  Magwa Cres"),
    GD05("GD05","CORNER BEECH AND ELM STREET, MARAIS STEYN PARK"),
    GD16("GD16","CNR FOURWAYS BLVD AND SHORT STREET"),
    GD17("GD17","SANDTON"),
    //GD20("GD20","CNR BARRY HERTZOG AND NAPIER"),
    GD48("GD48","Douglas Dr, Douglasdale"),
    GF09("GF09","FAMILY OAKFIELDS, Benoni"),
    HC05("HC05","P.O.BOX 92132 C/O 6TH & GRANT AVENUES"),
    HC10("HC10","P.O.BOX 1300 C/O ATTERBURY&SELIKATS STREETS"),
   /*
    Turn off till merchandisers in KZN sorted
    KC05("KC05","LA LUCIA SHOPPING CENTRE WILLIAM CAMPBELL&ARMSTRONG AVE"),
    KC24("KC24","CHRISTIANS VILLAGE CENTRE OLD MAIN ROAD"),
    KC56("KC56","HILTON COLLEGE ROAD & ELLZABETH AVENUE"),*/
    NC01("NC01","LYNWOOD CENTRE HIBISCUS ROAD"),
    //NC20("NC20","SHOP NO 30 HILLCREST BOULEVARD 220 LYNNWOOD RD HILLCREST"),
    NC24("NC24","WOODLANDS BOULEVARD CNR DE VILLEBOIS DR&GARSFONTEI"),
    //NC27("NC27","KAREE STREET SOUTHDOWNS"),
    NC29("NC29","CORNER GRAHAM&SILVERLAKES OVER LYNNWOOD ROAD"),
    NC30("NC30","CORNER NELLMAPLUS AND PIERRE VAN RYNEVELD DRIVE"),
    NC50("NC50","CNR SOLOMON MAHLANGU& JACQUELINE DRIVE"),
    //NC62("NC62","OLYMPUS PLAZA SHOPPING CENTRE CNR HANS STRYDOM AND HAYMEADOW"),
    //NC73("NC73","CNR RIGEL AND BUFFELSDRIFT, GP"),
    NC75("NC75","114 FEHRSEN STREET NIEUW MUCKLENEUK"),
    WC01("WC01","MILL STREET GARDENS"),
    //WC03("WC03","VICTORIA STREET CAMPS BAY"),
    WC04("WC04","KENILWORTH CENTRE DONCASTER STREET"),
    WC08("WC08","CNR DURBAN&TYGERVALLEY RDS TYGERVALLEY CENTRE"),
    WC10("WC10","ADELPHI CENTER MAIN RD"),
    WC11("WC11","HOWARD CENTRE HOWARD PLACE"),
    WC14("WC14","SHOP 129 CANAL WALK SHOPPING CENTRE"),
    WC16("WC16","LONG BEACH MALL STORE LONGBOAT ROAD MILKWOOD PARK, Noordhoek"),
    WC17("WC17","RIVERSIDE SHOPPING CENTRE MAIN ROAD"),
    WC18("WC18","CORNER CAMPGROUND&MAIN ROAD CLAREMONT"),
    WC21("WC21","CNR. SPAANSCHEMACHT & MAIN RDS CONSTANTIA"),
    WC23("WC23","N1 SHOPPING CENTRE VASCO BOULEVARD"),
    WC24("WC24","CNR BLAAUWBERG&OTTO DU PLESSIS RD"),
    WC27("WC27","V&A WHARF PANTRY V&A CENTRE"),
    WC29("WC29","CNR  BIRKENHEAD AND OTTO DU PLESSIS DRIVE"),
    WC34("WC34","TOKAI SHOPPING CENTRE MAIN RD"),
    WC36("WC36","MAIN ROAD SOMERSET WEST"),
    WC38("WC38","CORNER OF KOHLER&JONES STREET DRAKENSTEIN"),
    WC42("WC42","PLATTEKLOOF GERT VAN ROOYEN ROADS"),
    WC43("WC43","EVERSGLEN RETAIL CENTRE ERF 3806 EVERSGLEN"),
    WC50("WC50","Cavendish Road and Dreyer Street"),
    WC58("WC58","CNR OF ROSEMEAD AVE AND CARLTON ROAD"),
    WC60("WC60","CNR SENTINEL STREET AND MELKHOUT CRESCENT"),
    WC66("WC66","51, STELLENBOSCH SQUARE, STRAND ROAD"),
    //WC69("WC69","Cnr of Kommissaris & Sluysken Street"),
    WC72("WC72","BETWEEN R27 & SUNNINGDALE ROAD,TABLEVIEW"),
    //WC73("WC73","CNR. OTTO DUPLESSIS & SIR DAVID DR"),
    WC84("WC84","117 STRAND STREET"),
    NF75("NF75", "Family_x0020_Linton_x0027_s_x0020_Corner");

    //WC91("WC91","CORNER OF MAIN AND FRANCIS ROAD");






    private String storeCode;
    private String address;

    StoreInfoMapping(String storeCode, String itemId) {
        this.storeCode = storeCode;
        this.address = itemId;
    }


    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static String findItemIdByProductCode(String productCode){

        List<StoreInfoMapping> productMappings = Arrays.asList(values());

        StoreInfoMapping productMapping = productMappings.stream()
                .filter(mapping -> productCode.equals(mapping.getStoreCode()))
                .findFirst()
                .orElse(null);

        return productMapping.getAddress();
    }


    public static StoreInfoMapping findByStoreCode(String storeCode){

        List<StoreInfoMapping> storeInfoMappings = Arrays.asList(values());

        StoreInfoMapping storeMapping = storeInfoMappings.stream()
                .filter(mapping -> storeCode.equals(mapping.getStoreCode()))
                .findFirst()
                .orElse(null);

        return storeMapping;
    }

}
