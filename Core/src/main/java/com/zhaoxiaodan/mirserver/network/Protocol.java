package com.zhaoxiaodan.mirserver.network;

import java.util.HashMap;
import java.util.Map;

public enum Protocol {
	
	// For CM_IDPASSWORD Process
	CM_PROTOCOL(2000, null, null),
	CM_IDPASSWORD(2001, "Login", null),
	CM_IDPASSWORD_2(22001, "Login", null),
	
	CM_ADDNEWUSER(2002, "NewUser", null),
	CM_CHANGEPASSWORD(2003, null, null),
	CM_UPDATEUSER(2004, null, null),
	CM_SELECTSERVER(104, "SelectServer", null),
	
	SM_CERTIFICATION_FAIL(501, null, null),
	SM_ID_NOTFOUND(502, null, null),
	SM_PASSWD_FAIL(503, null, null),
	SM_NEWID_SUCCESS(504, null, null),
	SM_NEWID_FAIL(505, null, null),
	SM_PASSOK_SELECTSERVER(529, "LoginSuccSelectServer", null),
	SM_SELECTSERVER_OK(530, "SelectServerOk", null),
	
	// For Select Player Process
	CM_QUERYCHR(100, "QueryCharacter", null),
	CM_NEWCHR(101, "NewCharacter", null),
	CM_DELCHR(102, "DeleteCharacter", null),
	CM_SELCHR(103, "SelectCharacter", null),
	
	SM_QUERYCHR(520, "QueryCharactorOk", null),
	SM_NEWCHR_SUCCESS(521, null, null),
	SM_NEWCHR_FAIL(522, null, null),
	SM_DELCHR_SUCCESS(523, null, null),
	SM_DELCHR_FAIL(524, null, null),
	SM_STARTPLAY(525, "StartPlay", null),
	SM_STARTFAIL(526, null, null),
	SM_QUERYCHR_FAIL(527, null, null),
	
	// For game process
	// Client To Server Commands
	CM_GAMELOGIN(65001, "GameLogin", null),
	CM_QUERYUSERNAME(80, null, null),
	CM_QUERYBAGITEMS(81, "QueryBagItems", null),
	CM_QUERYUSERSTATE(82, "QueryUserState", null),
	
	CM_DROPITEM(1000, null, null),
	CM_PICKUP(1001, null, null),
	CM_TAKEONITEM(1003, "TakeOnOffItem", null),
	CM_TAKEOFFITEM(1004, "TakeOnOffItem", null),
	CM_BUTCH(1007, null, null),
	CM_MAGICKEYCHANGE(1008, "MagicKeyChange", null),
	CM_LOGINNOTICEOK(1018, "LoginNoticeOk", null),
	
	
	CM_EAT(1006, "Eat", null),
	
	CM_CLICKNPC(1010, "Merchant", null),
	CM_MERCHANTDLGSELECT(1011, "Merchant", null),
	CM_USERSELLITEM(1013, "SellItem", null),
	CM_WANTMINIMAP(1033, "WantMiniMap", null),
	
	CM_TURN(3010, "Action", null),
	CM_WALK(3011, "Action", null),
	CM_SITDOWN(3012, "Action", null),
	CM_RUN(3013, "Action", null),
	CM_HIT(3014, "Action", null),
	CM_HEAVYHIT(3015, "Action", null),
	CM_BIGHIT(3016, "Action", null),
	CM_SPELL(3017, "Spell", null),
	CM_POWERHIT(3018, "Action", null),
	CM_LONGHIT(3019, "Action", null),
	CM_WIDEHIT(3024, "Action", null),
	CM_FIREHIT(3025, "Action", null),
	CM_SAY(3030, "Say", null),
	CM_RIDE(3031, null, null),
	
	// Server to Client Commands
	SM_RUSH(6, null, null),
	SM_FIREHIT(8, null, null),
	SM_BACKSTEP(9, null, null),
	SM_TURN(10, "Turn", new int[]{8}),
	SM_WALK(11, null, null),
	SM_SITDOWN(12, null, null),
	SM_RUN(13, null, null),
	SM_HIT(14, null, null),
	SM_SPELL(17, null, null),
	SM_POWERHIT(18, null, null),
	SM_LONGHIT(19, null, null),
	SM_DIGUP(20, null, null),
	SM_DIGDOWN(21, null, null),
	SM_FLYAXE(22, null, null),
	SM_LIGHTING(23, null, null),
	SM_WIDEHIT(24, null, null),
	SM_DISAPPEAR(30, null, null),
	SM_STRUCK(31, null, null),
	SM_DEATH(32, null, null),
	SM_SKELETON(33, null, null),
	SM_NOWDEATH(34, null, null),
	
	SM_HEAR(40, null, null),
	SM_FEATURECHANGED(41, "FeatureChanged", null),
	SM_USERNAME(42, "UserName", null),
	SM_WINEXP(44, null, null),
	SM_LEVELUP(45, null, null),
	SM_DAYCHANGING(46, null, null),
	SM_LOGON(50, "", null),
	SM_NEWMAP(51, "NewMap", null),
	SM_ABILITY(52, null, null),
	SM_HEALTHSPELLCHANGED(53, null, null),
	SM_MAPDESCRIPTION(54, "MapDescription", null),
	SM_GAMEGOLDNAME(55, "GameGoldName", null),
	SM_SPELL2(117, null, null),
	
	SM_SYSMESSAGE(100, "SysMessage", null),
	SM_GROUPMESSAGE(101, null, null),
	SM_CRY(102, null, null),
	SM_WHISPER(103, null, null),
	SM_GUILDMESSAGE(104, null, null),
	
	SM_ADDITEM(200, null, null),
	SM_BAGITEMS(201, "BagItems", null),
	SM_DELITEM(202, null, null),

	SM_ADDMAGIC(210, "AddMagic", null),
	SM_SENDMYMAGIC(211, "SendMyMagics", new int[0]),
	SM_DELMAGIC(212, null, null),
	
	SM_DROPITEM_SUCCESS(600, null, null),
	SM_DROPITEM_FAIL(601, null, null),
	SM_ITEMSHOW(610, "ItemShow", null),
	SM_ITEMHIDE(611, null, null),
	SM_DOOROPEN(612, null, null),
	SM_TAKEON_OK(615, null, null),
	SM_TAKEON_FAIL(616, null, null),
	SM_TAKEOFF_OK(619, null, null),
	SM_TAKEOFF_FAIL(620, null, null),
	SM_SENDUSEITEMS(621, "SendUseItems", new int[0]),
	SM_WEIGHTCHANGED(622, null, null),
	SM_CLEAROBJECTS(633, null, null),
	SM_CHANGEMAP(634, null, null),
	SM_EAT_OK(635, null, null),
	SM_EAT_FAIL(636, null, null),
	SM_BUTCH(637, null, null),
	SM_MAGICFIRE(638, null, null),
	SM_MAGIC_LVEXP(640, null, null),
	SM_DURACHANGE(642, null, null),

	SM_MERCHANTSAY(643, "MerchantSay", null),
	SM_SENDUSERSELL(646, null, null),
	SM_USERSELLITEM_OK(648, null, null),
	SM_USERSELLITEM_FAIL(649, null, null),

	SM_GOLDCHANGED(653, null, null),
	SM_CHANGELIGHT(654, null, null),
	SM_CHANGENAMECOLOR(656, null, null),
	SM_CHARSTATUSCHANGED(657, null, null),
	SM_SENDNOTICE(658, "SendNotice", null),


	SM_AREASTATE(708, "AreaState", null),
	SM_DELITEMS(709, null, null),
	SM_READMINIMAP_OK(710, null, null),
	SM_READMINIMAP_FAIL(711, null, null),
	SM_SUBABILITY(752, null, null),

	SM_MENU_OK(767, null, null),

	SM_SHOWEVENT(804, "ShowEvent", null),
	SM_HIDEEVENT(805, null, null),
	SM_OPENHEALTH(1100, null, null),
	SM_CLOSEHEALTH(1101, null, null),
	SM_CHANGEFACE(1104, null, null),
	SM_VERSION_FAIL(1106, "VersionFail", null),
	SM_ITEMUPDATE(1500, null, null),
	SM_MONSTERSAY(1501, null, null),
	UNKNOWN_1000(11088, null, null);
	
	private static final Map<Short, Protocol> clientProtocols;
	private static final Map<Short, Protocol> serverProtocols;
	
	static {
		clientProtocols = new HashMap<>();
		serverProtocols = new HashMap<>();
		for (Protocol p : Protocol.values()) {
			if (p.name().charAt(0) == 'C') {
				if (clientProtocols.containsKey(p.id)) {
					System.out.println(("Protocol id " + p.id + " exsit by " + clientProtocols.get(p.id).name()));
				} else {
					clientProtocols.put(p.id, p);
				}
			} else {
				if (serverProtocols.containsKey(p.id)) {
					System.out.println(("Protocol id " + p.id + " exsit by " + serverProtocols.get(p.id).name()));
				} else {
					serverProtocols.put(p.id, p);
				}
			}
			
		}
	}
	
	public static Protocol getClientProtocol(short id) {
		if (clientProtocols.containsKey(id))
			return clientProtocols.get(id);
		else
			return null;
	}
	
	public static Protocol getServerProtocol(short id) {
		if (serverProtocols.containsKey(id))
			return serverProtocols.get(id);
		else
			return null;
	}
	
	public final short  id;
	public final String name;
	public final int[]  lenghtOfSections;
	
	Protocol(int id, String name, int[] lenghtOfSections) {
		this.id = (short) id;
		this.name = name;
		this.lenghtOfSections = lenghtOfSections;
	}
	
	
}
