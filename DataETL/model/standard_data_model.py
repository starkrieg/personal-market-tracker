class ValuationData:
    div_yield: float = None
    preco_over_lucro: float = None
    peg_ratio: float = None
    p_over_vp: float = None
    ev_over_ebitda: float = None
    ev_over_ebit: float = None
    p_over_ebitda: float = None
    p_over_ebit: float = None
    vpa: float = None
    p_over_ativo: float = None
    lpa: float = None
    p_over_sr: float = None
    p_over_cap_giro: float = None
    p_over_ativo_circ_liq: float = None
    payout: float = None

    def default(self, obj):
        return obj.__dict__
    ###
###

class GrowthData:
    cagr_receitas_5_anos: float = None
    cagr_lucros_5_anos: float = None

    def default(self, obj):
        return obj.__dict__
    ###
###

class ProfitData:
    roe: float = None
    roa: float = None
    roic: float = None
    giro_ativos: float = None

    def default(self, obj):
        return obj.__dict__
    ###
###

class EfficiencyData:
    margem_bruta: float = None
    margem_ebitda: float = None
    margem_ebit: float = None
    margem_liq: float = None

    def default(self, obj):
        return obj.__dict__
    ###
###

class DebtData:
    div_liq_over_pl: float = None
    div_liq_over_ebit: float = None
    div_liq_over_ebitda: float = None
    div_bruta_over_pl: float = None
    pl_over_ativos: float = None
    pass_over_ativos: float = None
    liq_corrente: float = None

    def default(self, obj):
        return obj.__dict__
    ###
###

class StandardizedData:
    valuationData: ValuationData
    growthData: GrowthData
    profitData: ProfitData
    efficiencyData: EfficiencyData
    debtData: DebtData

    # 2026-FEB-12 / StatusAlpha not public available anymore        
    # statusAlpha: StatusAlpha

    def __init__(self):
        self.valuationData = ValuationData()
        self.growthData = GrowthData()
        self.profitData = ProfitData()
        self.efficiencyData = EfficiencyData()
        self.debtData = DebtData()
        
        # 2026-FEB-12 / StatusAlpha not public available anymore
        #self.statusAlpha = StatusAlpha()
        
        pass
    ###

    def default(self, obj):
        return obj.__dict__
    ###
###

# returns a new StandardizedData object
def mergeStandardData(data1: StandardizedData, data2: StandardizedData):

    if data1 == {} or data1 == None:
        return data2
    ###
    if data2 == {} or data2 == None:
        return data1
    ###

    newData = StandardizedData()

    # merge philosophy
    # combine values from both objects
    # if both have values, take an AVG

    def mergeDataPiece(dataA = None, dataB = None):
        # assuming dataA and dataB are convertible to float
        # an error here means something must be changed in the processors!

        if dataA == None:
            return dataB
        if dataB == None:
            return dataA
        else:
            # response is AVG rounded to 2 decimal places
            return round( (( dataA + dataB ) / 2) , 2)
    ###

    ### valuation
    newData.valuationData.div_yield = mergeDataPiece(data1.valuationData.div_yield, data2.valuationData.div_yield)
    newData.valuationData.preco_over_lucro = mergeDataPiece(data1.valuationData.preco_over_lucro, data2.valuationData.preco_over_lucro)
    newData.valuationData.peg_ratio = mergeDataPiece(data1.valuationData.peg_ratio, data2.valuationData.peg_ratio)
    newData.valuationData.p_over_vp = mergeDataPiece(data1.valuationData.p_over_vp, data2.valuationData.p_over_vp)
    newData.valuationData.ev_over_ebitda = mergeDataPiece(data1.valuationData.ev_over_ebitda, data2.valuationData.ev_over_ebitda)
    newData.valuationData.ev_over_ebit = mergeDataPiece(data1.valuationData.ev_over_ebit, data2.valuationData.ev_over_ebit)
    newData.valuationData.p_over_ebitda = mergeDataPiece(data1.valuationData.p_over_ebitda, data2.valuationData.p_over_ebitda)
    newData.valuationData.p_over_ebit = mergeDataPiece(data1.valuationData.p_over_ebit, data2.valuationData.p_over_ebit)
    newData.valuationData.vpa = mergeDataPiece(data1.valuationData.vpa, data2.valuationData.vpa)
    newData.valuationData.p_over_ativo = mergeDataPiece(data1.valuationData.p_over_ativo, data2.valuationData.p_over_ativo)
    newData.valuationData.lpa = mergeDataPiece(data1.valuationData.lpa, data2.valuationData.lpa)
    newData.valuationData.p_over_sr = mergeDataPiece(data1.valuationData.p_over_sr, data2.valuationData.p_over_sr)
    newData.valuationData.p_over_cap_giro = mergeDataPiece(data1.valuationData.p_over_cap_giro, data2.valuationData.p_over_cap_giro)
    newData.valuationData.p_over_ativo_circ_liq = mergeDataPiece(data1.valuationData.p_over_ativo_circ_liq, data2.valuationData.p_over_ativo_circ_liq)

    ### growth
    newData.growthData.cagr_receitas_5_anos = mergeDataPiece(data1.growthData.cagr_receitas_5_anos, data2.growthData.cagr_receitas_5_anos)
    newData.growthData.cagr_lucros_5_anos = mergeDataPiece(data1.growthData.cagr_lucros_5_anos, data2.growthData.cagr_lucros_5_anos)

    ### profit
    newData.profitData.roe = mergeDataPiece(data1.profitData.roe, data2.profitData.roe)
    newData.profitData.roa = mergeDataPiece(data1.profitData.roa, data2.profitData.roa)
    newData.profitData.roic = mergeDataPiece(data1.profitData.roic, data2.profitData.roic)
    newData.profitData.giro_ativos = mergeDataPiece(data1.profitData.giro_ativos, data2.profitData.giro_ativos)

    ### efficiency
    newData.efficiencyData.margem_bruta = mergeDataPiece(data1.efficiencyData.margem_bruta, data2.efficiencyData.margem_bruta)
    newData.efficiencyData.margem_ebitda = mergeDataPiece(data1.efficiencyData.margem_ebitda, data2.efficiencyData.margem_ebitda)
    newData.efficiencyData.margem_ebit = mergeDataPiece(data1.efficiencyData.margem_ebit, data2.efficiencyData.margem_ebit)
    newData.efficiencyData.margem_liq = mergeDataPiece(data1.efficiencyData.margem_liq, data2.efficiencyData.margem_liq)

    ### debt
    newData.debtData.div_liq_over_pl = mergeDataPiece(data1.debtData.div_liq_over_pl, data2.debtData.div_liq_over_pl)
    newData.debtData.div_liq_over_ebit = mergeDataPiece(data1.debtData.div_liq_over_ebit, data2.debtData.div_liq_over_ebit)
    newData.debtData.pl_over_ativos = mergeDataPiece(data1.debtData.pl_over_ativos, data2.debtData.pl_over_ativos)
    newData.debtData.pass_over_ativos = mergeDataPiece(data1.debtData.pass_over_ativos, data2.debtData.pass_over_ativos)
    newData.debtData.liq_corrente = mergeDataPiece(data1.debtData.liq_corrente, data2.debtData.liq_corrente)
    
    return newData
###