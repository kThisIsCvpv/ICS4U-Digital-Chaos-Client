package com.kthisiscvpv.digitalchaos;

import com.kthisiscvpv.digitalchaos.misc.GameState;
import com.kthisiscvpv.digitalchaos.misc.RenderEngine;
import com.kthisiscvpv.digitalchaos.panel.ScreenFrame;
import com.kthisiscvpv.digitalchaos.render.MainMenuEngine;

@SuppressWarnings("deprecation")
public class DigitalChaos {

    private GameState gameState;
    private RenderEngine renderEngine;

    private ScreenFrame frame;

    public DigitalChaos() throws Exception {
        this.frame = new ScreenFrame(this);        
        
        this.setState(GameState.MAIN_MENU);
        this.setRenderEngine(new MainMenuEngine(this.frame));
        
        this.frame.setVisible(true);
    }

    public GameState getGameState() {
        return this.gameState;
    }

    public void setState(GameState state) {
        this.gameState = state;
    }

    public RenderEngine getRenderEngine() {
        return this.renderEngine;
    }

    public void setRenderEngine(RenderEngine engine) {
        if(this.renderEngine != null && this.renderEngine != engine) {
            if(this.renderEngine instanceof MainMenuEngine) {
                Thread runnable = ((MainMenuEngine)this.renderEngine).getInstance();
                runnable.interrupt();
                runnable.suspend();
            }
        }
        
        this.renderEngine = engine;
    }

    public ScreenFrame getFrame() {
        return this.frame;
    }
}
