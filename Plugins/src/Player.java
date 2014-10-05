public class Player {
	public static bCi core() {
		return bCe.bNO().bNP();
	}
	
	public static class CraftingInfo {
		public static aCh core() {
			return Player.getCraftingInfo();
		}
		
		public static String getName(Profession craft) {
			return cl.gg().b(43, craft.id, new Object[0]);
		}
		
		public static short getLevel(Profession craft) {
			return core().getLevel(craft.id);
		}
		
		public static long getExperience(Profession craft) {
			return core().lV(craft.id);
		}
		
		public static long getExperienceForLevel(short level) {
			return Fu.H(level);
		}
	}
	
	public dJN getTarget() {
		return core().ahB();
	}
	
	public static cjH actor() {
		return core().ahu();
	}
	
	public static cAI getPath(deO target) {
		return actor().c(target, true, true);
	}
	
	public static deO getLocation() {
		return core().Pi();
	}
	
	public static short getLevel() {
		return core().pj();
	}
	
	public static aCh getCraftingInfo() {
		return core().aXz();
	}
	
	public static DE getOrientation() {
		return actor().Q();
	}
	
	public static float getManhattenDistanceTo(cwn t) {
		return
			Math.abs(core().getWorldX() - t.getWorldX())
		+	Math.abs(core().getWorldY() - t.getWorldY());
	}
	
	// not sure how this is different than Pi();
	public static deO getPoint3D() {
		return actor().awR();
	}
}