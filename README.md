#Android Event Monitoring Framework (AEMF)
###Version 1.0

## Brief
The AEMF Framework is an application development Framework for Android devices that allow it to monitoring 
behavior applications according to the actions produced by them.

AEMF is based from the Automata Theory and implements a *behavioral conformance checking approach*, where 
the developer describe what behavior he is expecting
from the monitored application. Those behavior descriptions are defined as a finite automaton declared on XML files
(each automata is defined in his own file).

As in the Automata Theory, each automata needs his own alphabet to work, and the developer must provide it describing 
other XML file. 

Each arc from automaton represents an action to be performed by the application. If one automata performs that action,
then the automata changes from the currect state to the next given by the action performed.

Actually, the application actions are captured from the */dev/log/main* log file and it is readed in real time by 
the Framework. For each action readed, the framework parse it to a symbol (described in the alphabet file) and then 
each automata process it symbol and change their states.

The automaton, needs 3 types of states:
 - Initial State: Where the application start
 - Intermediate State: States between the initial and the final(s) states
 - Final State: Where the monitoring finish

Each Final State from the automata describe a non expecting behavior state (such a send SMS from a game without user permission).

Additionally, AEMF can write a log file which describes the monitoring process and it is write on the devices memory.


## How to use
AEMF is a .jar file which must to be imported on your dear Android IDE (such a Eclipse).

### 1 - Importing on IDE
Under Eclipse IDE, you must to:
 - Right click on your project
 - *Build Path > Java Build Path*
 - Press *Add External Jars*
 - Find your AEMF Jar file and then click *Ok*
 - Go to *Order and Export* and check the AEMF framework
 - Click on *Ok*

### 2 - Configuring AndroidManifest.xml
AEMF needs some permissions from the device. Add the following lines before the *<application>* tag:
<code><pre>
    &lt;uses-permission android:name="android.permission.READ_LOGS" /&gt;
    &lt;uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" /&gt;
</pre>
</code>

Then, you must to declare that the application use an *IntentService* that read the log (*/dev/log/main*).
Write the following inside the *<application></application>* block
<code>
<pre>
        &lt;service 
            android:name="cl.utfsm.aemf.BehaviorService"/&gt;
            &lt;intent-filter/&gt;
                &lt;action android:name="cl.utfsm.aemf.aemfservice"/&gt;/&lt;/action/&gt;
            &lt;/intent-filter/&gt;
        &lt;/service>
</pre>
</code>

### 3 - Coding!
Finally you can handle each event fired by AEMF. See the example below
<code><pre>
    //1 Configurar parametros comunes
    AEMFConfiguration.APPLICATION_ID_TO_BE_MONITORED = "cl.vasquez.MainPackage";
    AEMFConfiguration.AEMF_SOURCE_FILES_DIRECTORY 
    = Environment.getExternalStorageDirectory().getAbsolutePath() + "/AEMF_files/";	//points to /mnt/sdcard/AEMF_files/
    AEMFConfiguration.LOG_FILE_NAME = "log.txt";
    
    //2 Iniciar servicio listener
    Intent intent = new Intent(this, BehaviorService.class);
    startService(intent);
    
    //3 Suscribirse a los eventos
    BehaviorManager bm = new BehaviorManager();
    bm.addAutomataChangeStateEventListener(new AutomataListener() {
    
      @Override
      public void handleAutomataEvent(AutomataEvent e) {
      
      // The automata affected by a change
      Automata a = e.getAutomata();
      TextEvent t = e.getTextEvent();
      Symbol s = e.getSymbol();
      TransitionConfiguration p = e.getParameters();
      /**
        * Do anything you want with your automaton!
       */
      if(a.isFinished())
         System.out.println("El automata "+a.getId()+" (" + a.getFileName() + ") ha terminado llegando al estado " + a.getCurrentState().getId());
      else
         System.out.println("Automata " + a.getId() + " ha cambiado de estado a " + a.getCurrentState().getId() + " gracias a " + s.getId());
    }
    });
</pre></code>


