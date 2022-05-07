package Program;


class Client
{
	private String userName;
	private String password;
	private Double wallet;
	private String mailAddress;
	
	public Client() {}
	
	public Client(String userName, String password, Double wallet, String mailAddress)
	{
		this.userName = userName;
		this.password = password;
		this.wallet = wallet;
		this.mailAddress = mailAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Double getWallet() {
		return wallet;
	}

	public void setWallet(Double wallet) {
		this.wallet = wallet;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	
	
}

class Product
{
	private String productName;
	private Double price;
	private int unitsAvailable;
	
	public Product() {}
	
	public Product(String productName, Double price, int unitsAvailable, int cantBought)
	{
		this.productName = productName;
		this.price = price;
		this.unitsAvailable = unitsAvailable;
	}

	public void addStock(int stock)
	{
		this.unitsAvailable +=stock;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getUnitsAvailable() {
		return unitsAvailable;
	}

	public void setUnitsAvailable(int unitsAvailable) {
		this.unitsAvailable = unitsAvailable;
	}
	
	
}

class Sale
{
	private Product product;
	private int cantBought;
	
	public Sale() {}
	
	public Sale(Product product, int cantBought)
	{
		this.product = product;
		this.cantBought = cantBought;
	}

	public String getProductName() {
		return product.getProductName();
	}


	public int getCantBought() {
		return cantBought;
	}

	public void setCantBought(int cantBought) {
		this.cantBought = cantBought;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}