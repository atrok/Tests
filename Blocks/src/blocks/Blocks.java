package blocks;

public enum Blocks {
	
	FOURH(4,100),
	SIXH(6,150),
	NINEH(9,225),
	TWENTYH(20,480);
	
	private int value;
	private int price;

	private Blocks(int value, int price) {
		this.setValue(value);
		this.price=price;
		}
	public int getPrice(){
		return this.price;
	}
	public int getValue() {
		return value;
	}
	private void setValue(int value) {
		this.value = value;
	}
}
