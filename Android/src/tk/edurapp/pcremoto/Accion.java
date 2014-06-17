package tk.edurapp.pcremoto;

public class Accion {
	
    //CONSTANTES PARA LEER EL TIPO DE DATO
    public static final int LONGITUD 	= 3;
    public static final String TECLADO 	= "TEC";
    public static final String RATON 	= "RAT";
    public static final String TEXTO 	= "TXT";
    
    public static final String CONEXION = "CON";
    public static final String FINAL 	= "FIN";
	
    
	public String codigoEnvio(String inicio, int codigo){
		return inicio + codigo + FINAL;
	}
	
	public String codigoEnvio(int[] codigos){
		
		String codigoResultante = TECLADO;
		
		for (int i = 0; i<codigos.length; i++){
			codigoResultante += codigos[i] + ";";
		}
		codigoResultante += FINAL;
		
		return codigoResultante;
	}
	
	public String primeraConexion(){
		return CONEXION + android.os.Build.MODEL + FINAL;
	}
	
	//CONSTANTES RATÓN
	public static final int RATON_CLICK_CENTRAL			= 10000;
	public static final int RATON_CLICK_DERECHO			= 10001;
	public static final int RATON_CLICK_IZQUIERDO		= 10002;
	public static final int RATON_MOVIMIENTO_ARRIBA		= 10003;
	public static final int RATON_MOVIMIENTO_IZQUIERDA	= 10004;
	public static final int RATON_MOVIMIENTO_DERECHA	= 10005;
	public static final int RATON_MOVIMIENTO_ABAJO		= 10006;
	public static final int RATON_SCROLL_SUBIR			= 10007;
	public static final int RATON_SCROLL_BAJAR			= 10008;
    public static final int RATON_ZOOM_MAS              = 10009;
    public static final int RATON_ZOOM_MENOS            = 10010;
	
	//CONSTANTES TECLADO extraídas de la clase java.awt.event.KeyEvent
    public static final int VK_ENTER          = 0x0;
    public static final int VK_BACK_SPACE     = 0x1;
    public static final int VK_TAB            = 0x2;
    public static final int VK_CANCEL         = 0x03;
    public static final int VK_CLEAR          = 0x0C;
    public static final int VK_SHIFT          = 0x10;
    public static final int VK_CONTROL        = 0x11;
    public static final int VK_ALT            = 0x12;
    public static final int VK_PAUSE          = 0x13;
    public static final int VK_CAPS_LOCK      = 0x14;
    public static final int VK_ESCAPE         = 0x1B;
    public static final int VK_SPACE          = 0x20;
    public static final int VK_PAGE_UP        = 0x21;
    public static final int VK_PAGE_DOWN      = 0x22;
    public static final int VK_END            = 0x23;
    public static final int VK_HOME           = 0x24;
	
    public static final int VK_LEFT           = 0x25;
    public static final int VK_UP             = 0x26;
    public static final int VK_RIGHT          = 0x27;
    public static final int VK_DOWN           = 0x28;
	
    public static final int VK_COMMA          = 0x2C;
    public static final int VK_MINUS          = 0x2D;//-
    public static final int VK_PERIOD         = 0x2E;//.
    public static final int VK_SLASH          = 0x2F;// "/"
	
    public static final int VK_0              = 0x30;//NUMEROS, SE CORRESPONDEN CON SU VALOR EN ASCII
    public static final int VK_1              = 0x31;
    public static final int VK_2              = 0x32;
    public static final int VK_3              = 0x33;
    public static final int VK_4              = 0x34;
    public static final int VK_5              = 0x35;
    public static final int VK_6              = 0x36;
    public static final int VK_7              = 0x37;
    public static final int VK_8              = 0x38;
    public static final int VK_9              = 0x39;
	
    public static final int VK_SEMICOLON      = 0x3B;//PUNTO Y COMA
    public static final int VK_EQUALS         = 0x3D;//=
	
    public static final int VK_A              = 0x41;//LETRAS SE CORRESPONDEN CON SU VALOR EN ASCII
    public static final int VK_B              = 0x42;
    public static final int VK_C              = 0x43;
    public static final int VK_D              = 0x44;
    public static final int VK_E              = 0x45;
    public static final int VK_F              = 0x46;
    public static final int VK_G              = 0x47;
    public static final int VK_H              = 0x48;
    public static final int VK_I              = 0x49;
    public static final int VK_J              = 0x4A;
    public static final int VK_K              = 0x4B;
    public static final int VK_L              = 0x4C;
    public static final int VK_M              = 0x4D;
    public static final int VK_N              = 0x4E;
    public static final int VK_O              = 0x4F;
    public static final int VK_P              = 0x50;
    public static final int VK_Q              = 0x51;
    public static final int VK_R              = 0x52;
    public static final int VK_S              = 0x53;
    public static final int VK_T              = 0x54;
    public static final int VK_U              = 0x55;
    public static final int VK_V              = 0x56;
    public static final int VK_W              = 0x57;
    public static final int VK_X              = 0x58;
    public static final int VK_Y              = 0x59;
    public static final int VK_Z              = 0x5A;
	
    public static final int VK_OPEN_BRACKET   = 0x5B;//[
    public static final int VK_BACK_SLASH     = 0x5C;//"\"
    public static final int VK_CLOSE_BRACKET  = 0x5D;//]

    public static final int VK_NUMPAD0        = 0x60;//LOS VALORES DEL TECLADO NUMERICO
    public static final int VK_NUMPAD1        = 0x61;
    public static final int VK_NUMPAD2        = 0x62;
    public static final int VK_NUMPAD3        = 0x63;
    public static final int VK_NUMPAD4        = 0x64;
    public static final int VK_NUMPAD5        = 0x65;
    public static final int VK_NUMPAD6        = 0x66;
    public static final int VK_NUMPAD7        = 0x67;
    public static final int VK_NUMPAD8        = 0x68;
    public static final int VK_NUMPAD9        = 0x69;
    public static final int VK_MULTIPLY       = 0x6A;
    public static final int VK_ADD            = 0x6B;
	
    public static final int VK_SEPARATER      = 0x6C;
    public static final int VK_SEPARATOR      = VK_SEPARATER;

    public static final int VK_SUBTRACT       = 0x6D;
    public static final int VK_DECIMAL        = 0x6E;
    public static final int VK_DIVIDE         = 0x6F;
    public static final int VK_DELETE         = 0x7F; //TECLA "DEL/SUPR"
    public static final int VK_NUM_LOCK       = 0x90;
    public static final int VK_SCROLL_LOCK    = 0x91;

    public static final int VK_F1             = 0x70;//TECLAS DE FUNCIONES
    public static final int VK_F2             = 0x71;
    public static final int VK_F3             = 0x72;
    public static final int VK_F4             = 0x73;
    public static final int VK_F5             = 0x74;
    public static final int VK_F6             = 0x75;
    public static final int VK_F7             = 0x76;
    public static final int VK_F8             = 0x77;
    public static final int VK_F9             = 0x78;
    public static final int VK_F10            = 0x79;
    public static final int VK_F11            = 0x7A;
    public static final int VK_F12            = 0x7B;//HAY HASTA LA TECLA DE FUNCION 24, PERO CON 12 LLEGAN


    public static final int VK_PRINTSCREEN    = 0x9A;
    public static final int VK_INSERT         = 0x9B;
    public static final int VK_HELP           = 0x9C;
    public static final int VK_META           = 0x9D;

    public static final int VK_BACK_QUOTE     = 0xC0;
    public static final int VK_QUOTE          = 0xDE;

    /**
     * Constant for the numeric keypad <b>up</b> arrow key.
     * @see #VK_UP
     * @since 1.2
     */
    public static final int VK_KP_UP          = 0xE0;

    /**
     * Constant for the numeric keypad <b>down</b> arrow key.
     * @see #VK_DOWN
     * @since 1.2
     */
    public static final int VK_KP_DOWN        = 0xE1;

    /**
     * Constant for the numeric keypad <b>left</b> arrow key.
     * @see #VK_LEFT
     * @since 1.2
     */
    public static final int VK_KP_LEFT        = 0xE2;

    /**
     * Constant for the numeric keypad <b>right</b> arrow key.
     * @see #VK_RIGHT
     * @since 1.2
     */
    public static final int VK_KP_RIGHT       = 0xE3;

    /* For European keyboards */
    /** @since 1.2 */
    public static final int VK_DEAD_GRAVE               = 0x80;
    /** @since 1.2 */
    public static final int VK_DEAD_ACUTE               = 0x81;
    /** @since 1.2 */
    public static final int VK_DEAD_CIRCUMFLEX          = 0x82;
    /** @since 1.2 */
    public static final int VK_DEAD_TILDE               = 0x83;
    /** @since 1.2 */
    public static final int VK_DEAD_MACRON              = 0x84;
    /** @since 1.2 */
    public static final int VK_DEAD_BREVE               = 0x85;
    /** @since 1.2 */
    public static final int VK_DEAD_ABOVEDOT            = 0x86;
    /** @since 1.2 */
    public static final int VK_DEAD_DIAERESIS           = 0x87;
    /** @since 1.2 */
    public static final int VK_DEAD_ABOVERING           = 0x88;
    /** @since 1.2 */
    public static final int VK_DEAD_DOUBLEACUTE         = 0x89;
    /** @since 1.2 */
    public static final int VK_DEAD_CARON               = 0x8a;
    /** @since 1.2 */
    public static final int VK_DEAD_CEDILLA             = 0x8b;
    /** @since 1.2 */
    public static final int VK_DEAD_OGONEK              = 0x8c;
    /** @since 1.2 */
    public static final int VK_DEAD_IOTA                = 0x8d;
    /** @since 1.2 */
    public static final int VK_DEAD_VOICED_SOUND        = 0x8e;
    /** @since 1.2 */
    public static final int VK_DEAD_SEMIVOICED_SOUND    = 0x8f;

    /** @since 1.2 */
    public static final int VK_AMPERSAND                = 0x96;
    /** @since 1.2 */
    public static final int VK_ASTERISK                 = 0x97;
    /** @since 1.2 */
    public static final int VK_QUOTEDBL                 = 0x98;
    /** @since 1.2 */
    public static final int VK_LESS                     = 0x99;

    /** @since 1.2 */
    public static final int VK_GREATER                  = 0xa0;
    /** @since 1.2 */
    public static final int VK_BRACELEFT                = 0xa1;
    /** @since 1.2 */
    public static final int VK_BRACERIGHT               = 0xa2;

    public static final int VK_AT                       = 0x0200;//@

    public static final int VK_COLON                    = 0x0201;//:

    public static final int VK_CIRCUMFLEX               = 0x0202;//^


    public static final int VK_DOLLAR                   = 0x0203;//$

    
    public static final int VK_EURO_SIGN                = 0x0204;//€


    public static final int VK_EXCLAMATION_MARK         = 0x0205;//!


    public static final int VK_INVERTED_EXCLAMATION_MARK = 0x0206;//¡

    public static final int VK_LEFT_PARENTHESIS         = 0x0207;//(

    public static final int VK_NUMBER_SIGN              = 0x0208;//#

    public static final int VK_PLUS                     = 0x0209;//+


    public static final int VK_RIGHT_PARENTHESIS        = 0x020A;//)


    public static final int VK_UNDERSCORE               = 0x020B;//_

    public static final int VK_WINDOWS                  = 0x020C;//WIN
	
    public static final int VK_CONTEXT_MENU             = 0x020D;//tecla de menu(al lado de win derecha)

//???????????????????????????????????????????????????????????????????????????????????????
    /* for Sun keyboards */
    /** @since 1.2 */
    public static final int VK_CUT                      = 0xFFD1;
    /** @since 1.2 */
    public static final int VK_COPY                     = 0xFFCD;
    /** @since 1.2 */
    public static final int VK_PASTE                    = 0xFFCF;
    /** @since 1.2 */
    public static final int VK_UNDO                     = 0xFFCB;
    /** @since 1.2 */
    public static final int VK_AGAIN                    = 0xFFC9;
    /** @since 1.2 */
    public static final int VK_FIND                     = 0xFFD0;
    /** @since 1.2 */
    public static final int VK_PROPS                    = 0xFFCA;
    /** @since 1.2 */
    public static final int VK_STOP                     = 0xFFC8;

    /**
     * Constant for the Compose function key.
     * @since 1.2
     */
    public static final int VK_COMPOSE                  = 0xFF20;

    /**
     * Constant for the AltGraph function key.
     * @since 1.2
     */


    /**
     * Constant for the Begin key.
     * @since 1.5
     */
    public static final int VK_BEGIN                    = 0xFF58;

    /**
     * This value is used to indicate that the keyCode is unknown.
     * KEY_TYPED events do not have a keyCode value; this value
     * is used instead.
     */
    public static final int VK_UNDEFINED      = 0x0;
	
	public static final int[] LISTA_CODIGOS_TECLAS = { 0, 1, 2, 3, 12, 16, 17, 18, 19, 20, 27, 32, 33, 34,
			35, 36, 37, 38, 39, 40, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57,
			59, 61, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82,
			83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 96, 97, 98, 99, 100, 101, 102, 103,
			104, 105, 106, 107, 108, 60078, 109, 110, 111, 127, 144, 145, 112, 113, 114, 115,
			116, 117, 118, 119, 120, 121, 122, 123, 154, 155, 156, 157, 192, 222, 224, 225, 
			226, 227, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 
			142, 143, 150, 151, 152, 153, 160, 161, 162, 512, 513, 514, 515, 516, 517, 518, 
			519, 520, 521, 522, 523, 524, 525, 65489, 65485, 65487, 65483, 65481, 65488, 
			65482, 65480, 65312 };
	
	public static final String[] LISTA_NOMBRES_TECLAS = {"% ENTER", "% BACK SPACE", "% TAB", "% CANCEL", 
			"% CLEAR", "% SHIFT", "% CONTROL", "% ALT", "% PAUSE", "% CAPS LOCK", "% ESCAPE",
			"% SPACE", "% PAGE UP", "% PAGE DOWN", "% END", "% HOME", "% LEFT", "% UP", "% RIGHT",
			"% DOWN", "% COMMA", "% MINUS", "% PERIOD", "% SLASH", "% 0", "% 1", "% 2", "% 3", "% 4",
			"% 5", "% 6", "% 7", "% 8", "% 9", "% SEMICOLON", "% EQUALS", "% A", "% B", "% C", "% D",
			"% E", "% F", "% G", "% H", "% I", "% J", "% K", "% L", "% M", "% N", "% O", "% P", "% Q",
			"% R", "% S", "% T", "% U", "% V", "% W", "% X", "% Y", "% Z", "% OPEN BRACKET", 
			"% BACK SLASH", "% NUMPAD0", "% NUMPAD1", "% NUMPAD2", "% NUMPAD3", "% NUMPAD4", "% NUMPAD5",
			"% NUMPAD6", "% NUMPAD7", "% NUMPAD8", "% NUMPAD9", "% MULTIPLY", "% ADD", "% SEPARATER",
			"% SEPARATOR", "% SUBTRACT", "% DECIMAL", "% DIVIDE", "% DELETE", "% NUM LOCK",
			"% SCROLL LOCK", "% F1", "% F2", "% F3", "% F4", "% F5", "% F6", "% F7", "% F8", "% F9",
			"% F10", "% F11", "% F12", "% PRINTSCREEN", "% INSERT", "% HELP", "% META", "% BACK QUOTE",
			"% QUOTE", "% KP UP", "% KP DOWN", "% KP LEFT", "% KP RIGHT", "% DEAD GRAVE", "% DEAD ACUTE",
			"% DEAD CIRCUMFLEX", "% DEAD TILDE", "% DEAD MACRON", "% DEAD BREVE", "% DEAD ABOVEDOT",
			"% DEAD DIAERESIS", "% DEAD ABOVERING", "% DEAD DOUBLEACUTE", "% DEAD CARON",
			"% DEAD CEDILLA", "% DEAD OGONEK", "% DEAD IOTA", "% DEAD VOICED SOUND",
			"% DEAD SEMIVOICED SOUND", "% AMPERSAND", "% ASTERISK", "% QUOTEDBL", "% LESS",
			"% GREATER", "% BRACELEFT", "% BRACERIGHT", "% AT", "% COLON", "% CIRCUMFLEX", "% DOLLAR",
			"% EURO SIGN", "% EXCLAMATION MARK", "% LEFT PARENTHESIS", "% NUMBER SIGN", "% PLUS",
			"% RIGHT PARENTHESIS", "% UNDERSCORE", "% WINDOWS", "% CONTEXT MENU", "% CUT", "% COPY",
			"% PASTE", "% UNDO", "% AGAIN", "% FIND", "% PROPS", "% STOP", "% COMPOSE", "% BEGIN",
			"% UNDEFINED" };
    
}
