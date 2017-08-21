package musicinstruments;

public class CashDesk {
	
	private double money;
	
	public void addMoney(double money) {
		if(money>0) {
			this.money+=money;
		}else{
			System.out.println("Not enought money");
		}
	}

}

