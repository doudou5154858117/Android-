package in.srain.cube.views.ptr;

import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by caojian on 16/5/27.
 */
public class PtrUIHandlerHolderFoot implements PtrUIHandlerFoot {
    private PtrUIHandlerFoot mHandler;
    private PtrUIHandlerHolderFoot mNext;

    private boolean contains(PtrUIHandlerFoot handler) {
        return mHandler != null && mHandler == handler;
    }

    private PtrUIHandlerHolderFoot() {

    }

    public boolean hasHandler() {
        return mHandler != null;
    }

    private PtrUIHandlerFoot getHandler() {
        return mHandler;
    }

    public static void addHandler(PtrUIHandlerHolderFoot head, PtrUIHandlerFoot handler) {

        if (null == handler) {
            return;
        }
        if (head == null) {
            return;
        }
        if (null == head.mHandler) {
            head.mHandler = handler;
            return;
        }

        PtrUIHandlerHolderFoot current = head;
        for (; ; current = current.mNext) {

            // duplicated
            if (current.contains(handler)) {
                return;
            }
            if (current.mNext == null) {
                break;
            }
        }

        PtrUIHandlerHolderFoot newHolder = new PtrUIHandlerHolderFoot();
        newHolder.mHandler = handler;
        current.mNext = newHolder;
    }

    public static PtrUIHandlerHolderFoot create() {
        return new PtrUIHandlerHolderFoot();
    }

    public static PtrUIHandlerHolderFoot removeHandler(PtrUIHandlerHolderFoot head, PtrUIHandlerFoot handler) {
        if (head == null || handler == null || null == head.mHandler) {
            return head;
        }

        PtrUIHandlerHolderFoot current = head;
        PtrUIHandlerHolderFoot pre = null;
        do {

            // delete current: link pre to next, unlink next from current;
            // pre will no change, current move to next element;
            if (current.contains(handler)) {

                // current is head
                if (pre == null) {

                    head = current.mNext;
                    current.mNext = null;

                    current = head;
                } else {

                    pre.mNext = current.mNext;
                    current.mNext = null;
                    current = pre.mNext;
                }
            } else {
                pre = current;
                current = current.mNext;
            }

        } while (current != null);

        if (head == null) {
            head = new PtrUIHandlerHolderFoot();
        }
        return head;
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        PtrUIHandlerHolderFoot current = this;
        do {
            final PtrUIHandlerFoot handler = current.getHandler();
            if (null != handler) {
                handler.onUIReset(frame);
            }
        } while ((current = current.mNext) != null);
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        if (!hasHandler()) {
            return;
        }
        PtrUIHandlerHolderFoot current = this;
        do {
            final PtrUIHandlerFoot handler = current.getHandler();
            if (null != handler) {
                handler.onUIRefreshPrepare(frame);
            }
        } while ((current = current.mNext) != null);
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        PtrUIHandlerHolderFoot current = this;
        do {
            final PtrUIHandlerFoot handler = current.getHandler();
            if (null != handler) {
                handler.onUIRefreshBegin(frame);
            }
        } while ((current = current.mNext) != null);
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        PtrUIHandlerHolderFoot current = this;
        do {
            final PtrUIHandlerFoot handler = current.getHandler();
            if (null != handler) {
                handler.onUIRefreshComplete(frame);
            }
        } while ((current = current.mNext) != null);
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        PtrUIHandlerHolderFoot current = this;
        do {
            final PtrUIHandlerFoot handler = current.getHandler();
            if (null != handler) {
                handler.onUIPositionChange(frame, isUnderTouch, status, ptrIndicator);
            }
        } while ((current = current.mNext) != null);
    }
}
