package com.execute.main;

import java.util.List;

import org.snmp4j.mp.SnmpConstants;

import com.zipe.utils.network.SnmpUtil;

public class Snmp {

	public static void main(String[] args) throws Exception {
		SnmpUtil snmp = new SnmpUtil();
		snmp.initProperties("public", "10.8.0.1", 161L, SnmpConstants.version2c);
		List<String> resultList = snmp.execSnmpwalk("1.3.6.1.2.1.1.1","1.3.6.1.2.1.1.5");
		System.out.println(resultList.size());
	}

}
