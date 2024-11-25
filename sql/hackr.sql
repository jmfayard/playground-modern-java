-- bonds and stocks
(
    SELECT b.name, 'bonds', SUM(bp.cash_flow)
    FROM bonds b
    inner join bond_payments bp ON bp.bond_id = b.id
    group by b.id
    ORDER BY b.name
)
UNION
(
    SELECT s.name, 'stocks', SUM(sp.cash_flow)
    FROM stocks s
    inner join stock_payments sp ON sp.stock_id = s.id
    GROUP by s.id
    ORDER BY s.name
 )

