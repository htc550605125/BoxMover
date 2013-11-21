package htc550605125.boxmover.common.stage;

import htc550605125.boxmover.client.console.Utils;
import htc550605125.boxmover.common.vector.Dim2D;
import htc550605125.boxmover.common.vector.Vector;
import htc550605125.boxmover.common.element.Element;
import htc550605125.boxmover.common.element.ElementSet;
import htc550605125.boxmover.common.exception.CannotMoveException;
import htc550605125.boxmover.common.exception.OutOfMapException;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: htc
 * Date: 10/23/13
 * Time: 1:51 PM
 */
public class Stage extends StageBase implements Serializable, Cloneable, Comparable<Stage> {
    ElementSet data[] = null;

    @Override
    public Stage clone() {
        Stage ret = new Stage(info);
        ret.player = player;
        ret.data = new ElementSet[data.length];
        for (int i = 0; i < data.length; ++i)
            ret.data[i] = new ElementSet(data[i]);
        return ret;
    }

    public Stage(StageInfo info) {
        super(info);
        data = new ElementSet[info.dim().getMax()];
    }

    public Stage(Stage s) {
        this(s, s.info());
    }

    @Override()
    public int compareTo(Stage s) {
        return info.compareTo(s.info);
    }

    public Stage(Stage s, StageInfo info) {
        super(s.player, info);
        data = new ElementSet[s.data.length];
        for (int i = 0; i < data.length; ++i)
            data[i] = new ElementSet(s.data[i]);
    }

    public final ElementSet getPos(Vector v) throws OutOfMapException {
        return data[v.checkGet()];
    }

    public final ElementSet[] getRawData() {
        return data;
    }

    public Stage setPos(Vector v, ElementSet es) throws OutOfMapException, CannotMoveException{
        if (es.has(Element.PLAYER)) {
            if (player != null)
                throw new CannotMoveException(Element.PLAYER, player, v);
            player = v;
        }
        data[v.checkGet()] = new ElementSet(es);
        return this;
    }

    public Stage moveElement(Element e, Vector src, Vector dest) throws OutOfMapException, CannotMoveException{
        ElementSet s = getPos(src), d = getPos(dest);
        if (!s.has(e) || d.has(e))
            throw new CannotMoveException(e, src, dest);
        s.del(e); d.add(e);
        if (e == Element.PLAYER) player = dest;
        return this;
    }

    public boolean validate() {
        if (player == null) return false;
        int destCnt = 0, boxCnt = 0;
        for (ElementSet es : data) {
            if (es.has(Element.BOX)) ++boxCnt;
            if (es.has(Element.DEST)) ++destCnt;
        }
        return boxCnt == destCnt;
    }

    @Override
    public String toString() {
        StageView view = new StageView(this);
        Dim2D dim = (Dim2D) view.info().dim();
        StringBuilder ret = new StringBuilder();
        try {
            for (int i = 0; i < dim.x; ++i) {
                for (int j = 0; j < dim.y; ++j) {
                    ret.append(Utils.VIEWTEXT.get(view.get(dim.newVector(i, j))));
                    //logger.debug(view.get(dim.newVector(i, j)));
                }
                ret.append('\n');
            }
        }
        catch (OutOfMapException e) {
            logger.fatal(e);
            logger.fatal("At "+this.getClass());
            System.exit(-1);
        }
        return ret.toString();
    }
}