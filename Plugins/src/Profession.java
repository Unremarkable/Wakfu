

public enum Profession {
	Baker (40),
	Farmer (64),
	KamaMinter (67),
	CloseCombatWeaponsMaster (68),
	LongDistanceWeaponsMaster (69),
	AreaOfEffectWeaponsMaster (70),
	Lumberjack (71),
	Herbalist (72),
	Miner (73),
	Trapper (74),
	Fisherman (75),
	Chef (76),
	Armorer (77),
	Jeweler (78),
	Tailor (79),
	LeatherDealer (80),
	Handyman (81),
	GeneralCrafting (82);
	
	public int id; 
	private Profession(int id) {
		this.id = id;
	}
}