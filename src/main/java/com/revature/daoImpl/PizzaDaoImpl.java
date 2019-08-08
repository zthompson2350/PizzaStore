package com.revature.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Pizza;
import com.revature.util.ConnFactory;

public class PizzaDaoImpl {
	ConnFactory cf = ConnFactory.getInstance();

	public void OderPizza(int customerID, int pizzatype, int pizzasize) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "{ call orderPizza(?,?,?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setInt(1, customerID);
		call.setInt(2, pizzatype);
		call.setInt(3, pizzasize);
		call.execute();
	}

	public List<Pizza> getPreviousOrders(int customerID) throws SQLException {

		List<Pizza> pizzaList = new ArrayList<Pizza>();
		Connection conn = cf.getConnection();
		// Statement - compiled on SQL side; generally terrible (allows for SQL
		// injection) but since we're hardcoding it in, it's ok
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(
				"select typedescription, sizedescription, statusdescription "
				+ "from pizza join customer on pizza.customerID = customer.customerID  "
				+ "join pizzasize on pizza.pizzasizeID = pizzasize.sizeID "
				+ "join pizzatype on pizza.pizzatypeId = pizzatype.pizzatypeID "
				+ "join pizzastatus on pizza.statusID = pizzastatus.statusID where customer.customerId = "
						+ customerID);// no need for semicolon
		Pizza p = null;
		while (rs.next()) {
			p = new Pizza(rs.getString(1),rs.getString(2),rs.getString(3));
			pizzaList.add(p);
		}
		return pizzaList;
	}// end getPreviousOrders

}