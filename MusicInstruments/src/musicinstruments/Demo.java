package musicinstruments;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Shop shop = new Shop();
		System.out.println("Catalog:");
		shop.printCatalog();
		System.out.println();
		shop.printCatalogByAscendingPrice();
		System.out.println();
		shop.printCatalogByDescendingPrice();	
		System.out.println();		
		shop.printCatalogByName();	
		System.out.println();
		shop.acceptInstrument("Drums", 10);
		shop.acceptInstrument("Viola", 5);
		shop.acceptInstrument("Violin", 5);
		shop.printCatalog();	
		
		shop.sellInstrument("Contrabass", 1);
		shop.sellInstrument("Drums", 10);
		shop.sellInstrument("Clarinet", 1);
		shop.sellInstrument("Viola", 7);
		shop.sellInstrument("Violin", 4);
		shop.printCatalogByName();
		shop.printInfoForSoldInstruments();
		shop.printInfoForAccumulatdedMoneyFromSales();
		shop.printInfoForBestSellerInstrument();
		shop.printInfoForLeastSoldInstrument();
		shop.printInfoForBestSellerTypeOfInstrumentsBySoldUnits();
		shop.printInfoForBestSellerTypeOfInstrumentsByAccumulatedMoney();
	}

}