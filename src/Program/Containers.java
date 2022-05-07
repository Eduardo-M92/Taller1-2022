package Program;

class ClientContainer
{
private int max;
private int cant;
private Client[] clients;

	public ClientContainer(int max)
	{
		this.max = max;
		this.clients = new Client[max];
		
	}
	public boolean addClient(Client client)
	{
		if(cant<max)
		{
			clients[cant] = client;
			cant++;
			return true;
		}else { return false; }
	}
	
	public boolean deleteClient(String clientName)
	{
		int a;
		for(a=0;a<cant;a++)
		{
			if(clients[a].getUserName().toLowerCase().equals(clientName))
			{
				break;
			}
		}
		
		if(a==cant)
		{
			return false;
		}
		else
		{
			for(int b=a;b<cant;b++)
			{
				clients[b] = clients[b+1];
			}
			clients[cant] = null;
			cant--;
			return true;
		}
		
	}
	
	public Client getClientI(int position)
	{
		return clients[position];
	}
	
	public int getMax() 
	{
		return max;
	}
	public void setMax(int max) 
	{
		this.max = max;
	}
	public int getCant() 
	{
		return cant;
	}
	public void setCant(int cant) 
	{
		this.cant = cant;
	}
	
}

class ProductContainer
{
	private int max;
	private int cant;
	private double total;
	private Product[] products;

		public ProductContainer(int max)
		{
			this.max = max;
			this.products = new Product[max];
		}
		
		public boolean addProduct(Product product)
		{
			if(cant<max)
			{
				int a=0;
				for(a=0;a<cant;a++)
				{
					if(products[a].getProductName().toLowerCase().equals(product.getProductName().toLowerCase()))
					{
						products[a].setUnitsAvailable(products[a].getUnitsAvailable()+product.getUnitsAvailable());
						return true;
					}
				}
				if(a==cant)
				{
					products[cant] = product;
					cant++;
				}
				return true;
				
			}else { return false; }
		}
		
		public Product findProduct(String pName)
		{
			int a;
			for(a=0;a<cant;a++)
			{
				if(products[a].getProductName().toLowerCase().equals(pName.toLowerCase()))
				{
					return products[a];
				}
			}
			return null;
		}
		
		public Product getProductI(int position)
		{
			return products[position];
		}
		
		public int getMax() 
		{
			return max;
		}
		public void setMax(int max) 
		{
			this.max = max;
		}
		public int getCant() 
		{
			return cant;
		}
		public void setCant(int cant) 
		{
			this.cant = cant;
		}
		public Double getTotal() 
		{
			return total;
		}
}

class ShoppingCart
{
	private int max;
	private int cant;
	private double total;
	private Sale[] sales;

		public ShoppingCart(int max)
		{
			this.max = max;
			this.sales = new Sale[max];
		}
		
		public boolean addSale(Sale sale, int cant)
		{
			if(this.cant<max)
			{
				sales[this.cant] = sale;
				total += sale.getProduct().getPrice()*cant;
				this.cant++;
				return true;
			}else { return false; }
		}
		
		public Sale findSale(String pName)
		{
			int a;
			for(a=0;a<cant;a++)
			{
				if(sales[a].getProductName().toLowerCase().equals(pName.toLowerCase()))
				{
					return sales[a];
				}
			}
			return null;
		}
		
		public void deleteAll()
		{
			cant = 0;
			total = 0;
			for(int a=0;a<cant;a++)
			{
				sales[a]=null;
			}
		}
		
		public Sale getSaleI(int position)
		{
			return sales[position];
		}
		
		public int getMax() 
		{
			return max;
		}
		public void setMax(int max) 
		{
			this.max = max;
		}
		public int getCant() 
		{
			return cant;
		}
		public void setCant(int cant) 
		{
			this.cant = cant;
		}
		public Double getTotal() 
		{
			return total;
		}
}

class SaleContainer
{
	private int max;
	private int cant;
	private Sale[] sales;

		public SaleContainer(int max)
		{
			this.max = max;
			this.sales = new Sale[max];
		}
		
		public boolean addSale(Sale sale)
		{
			if(cant<max)
			{
				
				int a;
				for(a=0;a<cant;a++)
				{
					if(sales[a].getProductName().toLowerCase().equals(sale.getProductName().toLowerCase()))
					{
						sales[a].setCantBought(sales[a].getCantBought()+sale.getCantBought());
						break;
					}
				}
				if(a==cant)
				{
					sales[cant] = sale;
					cant++;
				}
				return true;
			}else { return false; }
		}
		
		public Sale findSale(String sName)
		{
			int a;
			for(a=0;a<cant;a++)
			{
				if(sales[a].getProductName().toLowerCase().equals(sName.toLowerCase()))
				{
					return sales[a];
				}
			}
			return null;
		}
		
		public Sale getSaleI(int position)
		{
			return sales[position];
		}
		
		public int getMax() 
		{
			return max;
		}
		public void setMax(int max) 
		{
			this.max = max;
		}
		public int getCant() 
		{
			return cant;
		}
		public void setCant(int cant) 
		{
			this.cant = cant;
		}
}