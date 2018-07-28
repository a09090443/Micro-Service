package com.zipe.utils.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.snmp4j.CommunityTarget;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TreeEvent;
import org.snmp4j.util.TreeUtils;

public class SnmpUtil {
	public static String MIB_2 = ".1.3.6.1.2.1";
	public static String SYSTEM_DESCIPTION = ".1.3.6.1.2.1.1.1";
	public static String SYSTEM_NAME = ".1.3.6.1.2.1.1.5";
	public static String INTERFACES = ".1.3.6.1.2.1.31.1.1.1.1";
	public static String AT = ".1.3.6.1.2.1.3";
	public static String IP = ".1.3.6.1.2.1.4";
	public static String ICMP = ".1.3.6.1.2.1.5";
	public static String TCP = ".1.3.6.1.2.1.6";
	public static String UDP = ".1.3.6.1.2.1.7";
	public static String EGP = ".1.3.6.1.2.1.8";
	public static String TRANSMISSION = ".1.3.6.1.2.1.10";
	public static String SNMP = ".1.3.6.1.2.1.11";
	public static String IN_OCTETS = ".1.3.6.1.2.1.2.2.1.10";
	public static String OUT_OCTETS = ".1.3.6.1.2.1.2.2.1.16";

	private String targetAddr;
	private String[] oidStr;
	private String commStr;
	private int snmpVersion;
	private Long portNum;

	public List<String> execSnmpwalk(String... oid) throws Exception {
		oidStr = oid;
		return process();
	}

	public List<String> execSnmpwalk(String communication, String ip, Long port, int version, String... oid)
			throws Exception {
		oidStr = oid;
		try {
			initProperties(communication, ip, port, version);
		} catch (Exception e) {
			throw e;
		}
		return process();
	}

	private List<String> process() throws Exception {

		Address targetAddress = GenericAddress.parse("udp:" + targetAddr + "/" + portNum);
		TransportMapping<? extends Address> transport = new DefaultUdpTransportMapping();
		Snmp snmp = new Snmp(transport);
		transport.listen();

		// setting up target
		CommunityTarget target = new CommunityTarget();
		target.setCommunity(new OctetString(commStr));
		target.setAddress(targetAddress);
		target.setRetries(3);
		target.setTimeout(1000 * 3);
		target.setVersion(snmpVersion);
		OID oid;
		List<String> resultList = new ArrayList<String>();

		for (String singleOid : oidStr) {
			try {
				oid = new OID(singleOid);
			} catch (Exception e) {
				System.err.println("Failed to understand the OID or object name.");
				throw e;
			}

			// Get MIB data.
			TreeUtils treeUtils = new TreeUtils(snmp, new DefaultPDUFactory());
			List<TreeEvent> events = treeUtils.getSubtree(target, oid);
			if (events == null || events.size() == 0) {
				System.out.println("No result returned.");
				System.exit(1);
			}

			// Handle the snmpwalk result.
			for (TreeEvent event : events) {
				if (event == null) {
					continue;
				}
				if (event.isError()) {
					System.err.println("oid [" + oid + "] " + event.getErrorMessage());
					continue;
				}

				VariableBinding[] varBindings = event.getVariableBindings();
				if (varBindings == null || varBindings.length == 0) {
					continue;
				}
				for (VariableBinding varBinding : varBindings) {
					if (varBinding == null) {
						continue;
					}
					// System.out.println(varBinding.getOid() + " : " +
					// varBinding.getVariable().getSyntaxString() + " : "
					// + varBinding.getVariable());
					resultList.add(varBinding.getOid() + " : " + varBinding.getVariable().getSyntaxString() + " : "
							+ varBinding.getVariable());
				}
			}
		}
		snmp.close();

		return resultList;
	}

	public void initProperties(String communication, String ip, Long port, int version) throws Exception {
		if (null != communication && communication != "") {
			commStr = communication;
		} else {
			throw new Exception("communication can not empty!!");
		}
		if (null != ip && ip != "") {
			targetAddr = ip;
		} else {
			throw new Exception("ip can not empty!!");
		}
		if (port > 0) {
			portNum = port;
		} else {
			throw new Exception("port can not empty!!");
		}
		if (version >= 0 && version < 3) {
			snmpVersion = version;
		} else {
			throw new Exception("version number has to be 0(v1), 1(v2c), 3(v3)!!");
		}
	}

}
