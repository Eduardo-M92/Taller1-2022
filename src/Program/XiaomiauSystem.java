package Program;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class XiaomiauSystem
{
	public static void main(String[] args) throws IOException
	{
		ClientContainer clients = new ClientContainer(1000);
		ProductContainer products = new ProductContainer(1000);
		ShoppingCart shoppingCart = new ShoppingCart(1000);
		SaleContainer sales = new SaleContainer(1000);
		int clientPos = -5;
		
		readArchs(clients, products, sales);
		
		System.out.println("Bienvenid@ a Xiaomiau Store");
		while(clientPos==-5)
		{
			clientPos = logInMenu(clients);
			if(clientPos!=-5)
			{
				break;
			}
		}
		if(clientPos == -13)
		{
			adminMenu(clients, products, sales);
		}
		else
		{
			userMenu(clientPos, shoppingCart, clients, products, sales);
		}
		
		exitSystem(clients,products,sales);
		
	}
	
	public static void readArchs(ClientContainer clients, ProductContainer products, SaleContainer sales) throws FileNotFoundException
	{
		Scanner arch1 = new Scanner(new File("Clientes.txt"));
		while(arch1.hasNext())
		{
			String[] parts = arch1.nextLine().split(",");
			Client cl = new Client();
			cl.setUserName(parts[0]);
			cl.setPassword(parts[1]);
			cl.setWallet(Double.parseDouble(parts[2]));
			cl.setMailAddress(parts[3]);
			
			clients.addClient(cl);
		}
		
		Scanner arch2 = new Scanner(new File("Productos.txt"));
		while(arch2.hasNext())
		{
			String[] parts = arch2.nextLine().split(",");
			Product pr = new Product();
			pr.setProductName(parts[0]);
			pr.setPrice(Double.parseDouble(parts[1]));
			pr.setUnitsAvailable(Integer.parseInt(parts[2]));
			
			products.addProduct(pr);
		}
		
		Scanner arch3 = new Scanner(new File("Ventas.txt"));
		while(arch3.hasNext())
		{
			String[] parts = arch3.nextLine().split(",");
			Sale sa = new Sale();
			Product product = products.findProduct(parts[0]) ;
			sa.setCantBought(Integer.parseInt(parts[1]));
			sa.setProduct(product);
			
			sales.addSale(sa);
		}
	}
	
	public static int logInMenu(ClientContainer clients)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Iniciar sesión");
		System.out.print("Ingrese su nombre de usuario: ");
		String userName = sc.nextLine();
		int a;
		String password="";
		
		if(userName.equals("ADMIN"))
		{
			System.out.print("Ingrese contraseña: ");
			password = sc.nextLine();
			if(password.equals("NYAXIO"))
			{
				return -13;
			}
		}
		
		else
		{
			for(a=0;a<clients.getCant();a++)
			{
				if(clients.getClientI(a).getUserName().equals(userName))
				{
					break;
				}
			}
			if(a!=clients.getCant())
			{
				System.out.print("Ingrese contraseña: ");
				password = sc.nextLine();
				if(clients.getClientI(a).getPassword().equals(password))
				{
					System.out.println("--------------------------");
					System.out.println("Inico de sesion exitoso");
					System.out.println("--------------------------");
					return a;
				}else { System.out.println("Contraseña incorrecta");}
			}
			else
			{
				System.out.print("Usuario no encontrado, desea registrarse(Si , No): ");
				String option = sc.nextLine().toLowerCase();
				if(option.equals("si")) { return (registerMenu(clients)); }
				
			}
		}
			
		return -5;
	}
	
	public static int registerMenu(ClientContainer clients)
	{
		System.out.println("Menu de registro");
		Scanner sc = new Scanner(System.in);
		String name = "";
		String password = "";
		String mail = "";
		Double wallet = -1.00;
		
		
		while(name=="" )
		{
			System.out.print("Ingrese su nombre de usuario: ");
			name = sc.nextLine();
		}
		
		while(password=="")
		{
			System.out.print("Ingrese su Contraseña: ");
			password = sc.nextLine();
		}
		
		while(mail=="")
		{
			System.out.print("Ingrese su direccion de Correo electronico: ");
			mail = sc.nextLine();
		}
		while(wallet<0)
		{
			System.out.print("Ingrese un deposito inicial(pude ser 0$): ");
			wallet = sc.nextDouble();
			sc.nextLine();
		}
		Client cl = new Client(name, password, wallet, mail);
		clients.addClient(cl);
		return (clients.getCant()-1);
	}
	
	public static void userMenu(int clientPos, ShoppingCart shoppingCart, ClientContainer clients, ProductContainer products, SaleContainer sales)
	{
		Client currentClient = clients.getClientI(clientPos);
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		while(!exit)
		{
			System.out.println("----------------------------------");
			System.out.println("Bienvenid@ a Xiaomiau Store "+clients.getClientI(clientPos).getUserName());
			System.out.println("----------------------------------");
			System.out.println("Menu: ");
			System.out.println("a)Elegir producto a comprar");
			System.out.println("b)Cambiar contraseña");
			System.out.println("c)Ver catalogo de productos");
			System.out.println("d)Ver saldo disponible");
			System.out.println("e)Recargar saldo");
			System.out.println("f)Ver carrito");
			System.out.println("g)Quitar carrito");
			System.out.println("h)Pagar carrito");
			System.out.println("i)Salir");
			System.out.print("Ingrese operacion a realizar: ");
			String option = sc.nextLine().toLowerCase();
			while(!option.equals("a")&&!option.equals("b")&&!option.equals("c")&&!option.equals("d")&&!option.equals("e")&&!option.equals("f")&&!option.equals("g")&&!option.equals("h")&&!option.equals("i"))
			{
				System.out.println("Menu: ");
				System.out.println("a)Elegir producto a comprar");
				System.out.println("b)Cambiar contraseña");
				System.out.println("c)Ver catalogo de productos");
				System.out.println("d)Ver saldo disponible");
				System.out.println("e)Recargar saldo");
				System.out.println("f)Ver carrito");
				System.out.println("g)Quitar carrito");
				System.out.println("h)Pagar carrito");
				System.out.println("i)Salir");
				System.out.print("Ingrese operacion a realizar: ");
				option = sc.nextLine().toLowerCase();
			}
			switch(option)
			{
			case "a":
				buyProduct(products, sales, shoppingCart, currentClient);
				break;
			case "b":
				changePassword(currentClient);
				break;
			case "c":
				seeProducts(products);
				break;
			case "d":
				System.out.println("Saldo en la cartera: "+currentClient.getWallet());
				break;
			case "e":
				System.out.print("Ingrase saldo a recargar: ");
				Double money = sc.nextDouble();
				sc.nextLine();
				while(money<=0)
				{
					System.out.println("Saldo no puede ser negativo");
					System.out.print("Ingrase saldo a recargar ");
					money = sc.nextDouble();
					sc.nextLine();
				}
				currentClient.setWallet(currentClient.getWallet()+money);
				break;
			case "f":
				seeShoppingCart(shoppingCart);
				break;
			case "g":
				deleteCart(shoppingCart, products, sales);
				break;
			case "h":
				buyCart(shoppingCart, currentClient, sales);
				break;
			case "i":
				exit = true;
				break;
				
			}
		}
	}
	
	public static void buyProduct(ProductContainer products, SaleContainer sales, ShoppingCart shoppingCart, Client currentClient)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Menu de compra");
		System.out.print("Ingrese el nombre del producto a comprar: ");
		String buyName = sc.nextLine().toLowerCase();
		
		while(products.findProduct(buyName)==null&&!buyName.equals("cancelar"))
		{
			System.out.println("---------------------");
			System.out.println("Nombre incorrecto");
			System.out.println("---------------------");
			System.out.print("Ingrese el nombre del producto a comprar(cancelar para salir): ");
			buyName = sc.nextLine();
		}
		if(buyName.equals("cancelar")){ System.out.println("Saliendo..."); }
		else
		{
			Product product = products.findProduct(buyName);
			System.out.print("Ingrase una cantidad a comprar: ");
			int cant = sc.nextInt();
			sc.nextLine();
			if(cant>product.getUnitsAvailable())
			{
				System.out.println("Sin stock suficiente, unidades disponibles "+product.getUnitsAvailable());
			}
			else
			{
				Sale temSale = new Sale();
				temSale.setCantBought(cant);
				temSale.setProduct(product);
				shoppingCart.addSale(temSale, cant);
				product.setUnitsAvailable(product.getUnitsAvailable()-cant);
				//sales.addSale(temSale);
				//sales.findSale(buyName.toLowerCase()).setCantBought(cant+sales.findSale(buyName.toLowerCase()).getCantBought());
				System.out.println("-------------------------------");
				System.out.println(product.getProductName()+" añadido al carrito, unidades añadidas: "+cant);
				System.out.println("-------------------------------");
			}
		}
		
	}
	
	public static void changePassword(Client currentClient)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Menu cambio de contraseña");
		System.out.print("Ingrase su nueva Contraseña: ");
		String password = sc.nextLine();
		currentClient.setPassword(password);
	}

	public static void seeProducts(ProductContainer products)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Catalogo de productos disponibles(Ingrese enter para continuar)");
		for(int a=0;a<products.getCant();a++)
		{
			if(products.getProductI(a).getUnitsAvailable()>0)
			{
				System.out.println("Producto: "+products.getProductI(a).getProductName()+". Stock: "+products.getProductI(a).getUnitsAvailable()+". Precio: "+products.getProductI(a).getPrice()+".");
			}
		}
		String continu = sc.nextLine();
	}
	
	public static void seeShoppingCart(ShoppingCart shoppingCart)
	{
		System.out.println("------------------------------------");
		System.out.println("Desplegando carrito de compra...");
		System.out.println("------------------------------------");
		for(int a=0;a<shoppingCart.getCant();a++)
		{
			System.out.println("Producto: "+shoppingCart.getSaleI(a).getProductName()+". Cantidad: "+shoppingCart.getSaleI(a).getCantBought()+".");
		}
	}

	public static void deleteCart(ShoppingCart shoppingCart, ProductContainer products, SaleContainer sales)
	{
		for(int a=0;a<shoppingCart.getCant();a++)
		{
			Product actuallyProduct = products.findProduct(shoppingCart.getSaleI(a).getProductName());
			actuallyProduct.setUnitsAvailable(actuallyProduct.getUnitsAvailable()+shoppingCart.getSaleI(a).getCantBought());
		}
		shoppingCart.deleteAll();
	}
	
	public static void buyCart(ShoppingCart shoppingCart, Client currentClient, SaleContainer sales)
	{
		System.out.println("Comprando...");
		if(currentClient.getWallet()>=shoppingCart.getTotal())
		{
			currentClient.setWallet(currentClient.getWallet()-shoppingCart.getTotal());
			for(int a=0; a<shoppingCart.getCant();a++)
			{
				sales.addSale(shoppingCart.getSaleI(a));
			}
			System.out.println("Compra realizada con exito...");
			shoppingCart.deleteAll();
		}
		else { System.out.println("Saldo insuficiente..."); }
	}

	public static void exitSystem(ClientContainer clients, ProductContainer products, SaleContainer sales) throws IOException
	{
		FileWriter wClients = new FileWriter("Clientes.txt");
		PrintWriter pw1 = new PrintWriter(wClients);
		for (int i = 0; i<clients.getCant(); i++)
		{
			Client cl = clients.getClientI(i);
			pw1.println(cl.getUserName()+","+cl.getPassword()+","+cl.getWallet()+","+cl.getMailAddress());
		}
		
		FileWriter wProducts = new FileWriter("Productos.txt");
		PrintWriter pw2 = new PrintWriter(wProducts);
		for (int i = 0; i<products.getCant(); i++)
		{
			Product pr = products.getProductI(i);
			pw2.println(pr.getProductName()+","+pr.getPrice()+","+pr.getUnitsAvailable());
		}
		
		FileWriter wSales = new FileWriter("Ventas.txt");
		PrintWriter pw3 = new PrintWriter(wSales);
		for (int i = 0; i<sales.getCant(); i++)
		{
			Sale sl = sales.getSaleI(i);
			pw3.println(sl.getProductName()+","+sl.getCantBought());
		}
		System.out.println("Saliendo del sistema...");
		wClients.close();
		wProducts.close();
		wSales.close();
	}
	
	public static void adminMenu(ClientContainer clients, ProductContainer products, SaleContainer sales)
	{
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		while(!exit)
		{
			System.out.println("Bienvenido al menu de Administración");
			System.out.println("Menu: ");
			System.out.println("a)Bloquear usuario");
			System.out.println("b)Ver historial de compras");
			System.out.println("c)Agregar Producto");
			System.out.println("d)Agregar stock");
			System.out.println("e)Actualizar datos");
			System.out.println("f)Salir");
			System.out.print("Ingrese opcion: ");
			String option = sc.nextLine().toLowerCase();
			while(!option.equals("a")&&!option.equals("b")&&!option.equals("c")&&!option.equals("d")&&!option.equals("e")&&!option.equals("f"))
			{
				System.out.println("Menu: ");
				System.out.println("a)Bloquear usuario");
				System.out.println("b)Ver historial de compras");
				System.out.println("c)Agregar Producto");
				System.out.println("d)Agregar stock");
				System.out.println("e)Actualizar datos");
				System.out.println("f)Salir");
				System.out.print("Ingrese opcion: ");
				option = sc.nextLine().toLowerCase();
			}
			switch(option)
			{
			case "a":
				System.out.print("Ingrese nombre del usuario: ");
				String deleteName = sc.nextLine().toLowerCase();
				if(clients.deleteClient(deleteName))
				{
					System.out.println("Cliente "+deleteName+" eliminado del sistema");
				}else { System.out.println("El cliente no existe"); }
				break;
			case "b":
				System.out.println("----------------------------------------");
				System.out.println("Desplegando Historial de compras...");
				System.out.println("----------------------------------------");
				for(int a=0;a<sales.getCant();a++)
				{
					System.out.println("Producto: "+sales.getSaleI(a).getProductName()+". Veces comprado: "+sales.getSaleI(a).getCantBought());
				}
				break;
			case "c":
				addProduct(products);
				break;
			case "d":
				addStock(products);
				break;
			case "e":
				changePrice(products);
				break;
			case "f":
				exit = true;
				break;
			}
		}
	}
	
	public static void addProduct(ProductContainer products)
	{
		Scanner sc = new Scanner(System.in);
		String name;
		Double price;
		int cant;
		System.out.print("Ingrese el nombre del nuevo producto: ");
		name = sc.nextLine();
		System.out.print("Ingrese el precio del nuevo producto: ");
		price = sc.nextDouble();
		sc.nextLine();
		System.out.print("Ingrese el Stock inicial del nuevo producto: ");
		cant = sc.nextInt();
		sc.nextLine();
		Product pr = new Product(name, price, cant, 0);
		products.addProduct(pr);
		System.out.println("Producto añadido con exito...");
	}
	
	public static void addStock(ProductContainer products)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Ingrese el nombre del producto a ingresar stock: ");
		String name = sc.nextLine().toLowerCase();
		while(products.findProduct(name)==null)
		{
			System.out.print("Ingrese el nombre del producto a ingresar stock: ");
			name = sc.nextLine().toLowerCase();
		}
		System.out.print("Ingrese el nombre del producto a ingresar stock: ");
		int stock = sc.nextInt();
		products.findProduct(name).addStock(stock);
		
		System.out.println("Stock añadido nuevo stock: "+products.findProduct(name).getUnitsAvailable());
	}
	
	public static void changePrice(ProductContainer products)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Ingrece el nombre del producto a cambiar precio: ");
		String name = sc.nextLine();
		while(products.findProduct(name)==null)
		{
			System.out.print("Ingrece el nombre del producto a cambiar precio: ");
			name = sc.nextLine();
		}
		double price = sc.nextDouble();
		sc.nextLine();
		products.findProduct(name).setPrice(price);
	}
}
