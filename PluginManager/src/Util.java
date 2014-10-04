import java.util.Iterator;

public class Util {
	public static void print(String str) {
		aGD text = new aGD(str);
		text.nb(4);
		DJ.Ow().a(text);
	}
	
	public static Dx getCharacterInfoManager() {
		return Dx.NX();
	}
	
	public static Iterator<TW> getCharacters() {
		return getCharacterInfoManager().Ob();
	}
	
	public static dJN getCharacterTarget(TW character) {
		return character.ahB();
	}
}
